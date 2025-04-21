$(document).ready(function() {
    const BASE_URL = "http://localhost:8080/api/v1/attendance";

    // Load all attendance records when page loads
    loadAttendanceData();

    // Refresh button event
    $("#refreshBtn").click(function() {
        loadAttendanceData();
    });

    // Date filter event
    $("#attendanceDate").change(function() {
        loadAttendanceData();
    });

    // Save attendance button event
    $("#saveAttendanceBtn").click(function() {
        saveAttendance();
    });

    // Function to load all attendance data
    function loadAttendanceData() {
        $.ajax({
            url: BASE_URL,
            type: "GET",
            dataType: "json",
            success: function(response) {
                displayAttendanceData(response);
                updateAttendanceStats(response);
            },
            error: function(xhr, status, error) {
                console.error("Error loading attendance data:", error);
                alert("Failed to load attendance data. Please try again.");
            }
        });
    }

    // Function to display attendance data in the table
    function displayAttendanceData(data) {
        let tableContent = "";

        if (data && data.length > 0) {
            $.each(data, function(index, attendance) {
                // Format date (assuming date comes as ISO string)
                const attendanceDate = new Date(attendance.attendanceDate);
                const formattedDate = attendanceDate.toLocaleDateString();

                // Determine status based on onTime (simplified logic - should be adjusted based on your requirements)
                let status = "present";
                let statusBadge = '<span class="badge bg-success status-badge status-present">Present</span>';

                // If there is a way to determine late vs absent status from your data
                // This is a simplified example
                const onTime = attendance.onTime ? attendance.onTime : '--:--';
                const offTime = attendance.offTime ? attendance.offTime : '--:--';

                tableContent += `
                    <tr>
                        <td>${attendance.id}</td>
                        <td>Employee #${attendance.employeeId}</td>
                        <td>${formattedDate}</td>
                        <td><span class="time-badge">${onTime}</span></td>
                        <td><span class="time-badge">${offTime}</span></td>
                        <td>${statusBadge}</td>
                        <td>
                            <div class="d-flex gap-2">
                                <button class="btn btn-sm btn-outline-primary edit-btn" data-id="${attendance.id}">
                                    <i class="fas fa-edit"></i>
                                </button>
                                <button class="btn btn-sm btn-outline-danger delete-btn" data-id="${attendance.id}">
                                    <i class="fas fa-trash-alt"></i>
                                </button>
                            </div>
                        </td>
                    </tr>
                `;
            });
        } else {
            tableContent = `
                <tr>
                    <td colspan="7" class="text-center">No attendance records found</td>
                </tr>
            `;
        }

        $("#attendanceTableBody").html(tableContent);

        // Attach event handlers to edit and delete buttons
        $(".edit-btn").click(function() {
            const attendanceId = $(this).data("id");
            openEditModal(attendanceId);
        });

        $(".delete-btn").click(function() {
            const attendanceId = $(this).data("id");
            deleteAttendance(attendanceId);
        });
    }

    // Function to update attendance statistics
    function updateAttendanceStats(data) {
        // This is a simplified example - you'd need to implement proper counting logic
        // based on your actual data structure and business rules

        let presentCount = 0;
        let lateCount = 0;
        let absentCount = 0;
        let totalEmployees = 32; // This should be dynamically fetched or calculated

        // Count present and late employees
        if (data && data.length > 0) {
            presentCount = data.length;

            // Implement your logic to determine late employees
            // This is an example calculation - adjust based on your business rules
            data.forEach(function(attendance) {
                // Example: if onTime is after 9:00 AM, consider late
                if (attendance.onTime && attendance.onTime > "09:00:00") {
                    lateCount++;
                    presentCount--; // Adjust present count
                }
            });
        }

        // Calculate absent (total employees minus present and late)
        absentCount = totalEmployees - (presentCount + lateCount);

        // Update the dashboard stats
        $(".stat-card.present h2").text(presentCount);
        $(".stat-card.late h2").text(lateCount);
        $(".stat-card.absent h2").text(absentCount);
        $(".stat-card.total h2").text(totalEmployees);
    }

    // Function to open edit modal with attendance data
    function openEditModal(attendanceId) {
        $.ajax({
            url: `${BASE_URL}/${attendanceId}`,
            type: "GET",
            dataType: "json",
            success: function(attendance) {
                // Populate modal with attendance data
                $("#employeeId").val(attendance.employeeId);

                // Format date for input field (YYYY-MM-DD)
                const attendanceDate = new Date(attendance.attendanceDate);
                const formattedDate = attendanceDate.toISOString().split('T')[0];
                $("#modalAttendanceDate").val(formattedDate);

                // Set time values
                $("#clockInTime").val(attendance.onTime);
                $("#clockOutTime").val(attendance.offTime);

                // Set status based on your business logic
                // This is just an example
                let status = "present";
                if (attendance.onTime > "09:00:00") {
                    status = "late";
                }
                $("#status").val(status);

                // Set hidden ID field for the form (you need to add this to your HTML)
                // Add this field to your modal HTML: <input type="hidden" id="attendanceId">
                $("#attendanceId").val(attendance.id);

                // Change modal title and button text
                $("#addAttendanceModalLabel").text("Edit Attendance");
                $("#saveAttendanceBtn").text("Update Attendance");

                // Show the modal
                $("#addAttendanceModal").modal("show");
            },
            error: function(xhr, status, error) {
                console.error("Error fetching attendance data:", error);
                alert("Failed to load attendance data for editing. Please try again.");
            }
        });
    }

    // Function to reset modal form when opening for new attendance
    $("#addAttendanceModal").on("hidden.bs.modal", function() {
        // Reset form
        $("#attendanceForm")[0].reset();

        // Reset default values
        const today = new Date().toISOString().split('T')[0];
        $("#modalAttendanceDate").val(today);

        // Reset modal title and button text
        $("#addAttendanceModalLabel").text("Record Attendance");
        $("#saveAttendanceBtn").text("Save Attendance");

        // Reset hidden ID field if it exists
        if ($("#attendanceId").length) {
            $("#attendanceId").val("");
        }
    });

    // Function to save or update attendance
    function saveAttendance() {
        // Get form data
        const attendanceId = $("#attendanceId").length ? $("#attendanceId").val() : null;
        const employeeId = $("#employeeId").val();
        const attendanceDate = $("#modalAttendanceDate").val();
        const clockInTime = $("#clockInTime").val();
        const clockOutTime = $("#clockOutTime").val() || null;

        // Validate form data
        if (!employeeId || !attendanceDate || !clockInTime) {
            alert("Please fill in all required fields.");
            return;
        }

        // Prepare data object
        const attendanceData = {
            id: attendanceId || null,
            employeeId: parseInt(employeeId),
            attendanceDate: attendanceDate,
            onTime: clockInTime,
            offTime: clockOutTime
        };

        // Determine if it's an update or a new record
        const isUpdate = attendanceId ? true : false;
        const url = isUpdate ? `${BASE_URL}/update` : `${BASE_URL}/save`;
        const method = isUpdate ? "PUT" : "POST";

        // Send AJAX request
        $.ajax({
            url: url,
            type: method,
            contentType: "application/json",
            data: JSON.stringify(attendanceData),
            success: function(response) {
                // Close modal
                $("#addAttendanceModal").modal("hide");

                // Reload data
                loadAttendanceData();

                // Show success message
                alert(isUpdate ? "Attendance updated successfully!" : "Attendance saved successfully!");
            },
            error: function(xhr, status, error) {
                console.error("Error saving attendance:", error);
                alert("Failed to save attendance. Please try again.");
            }
        });
    }

    // Function to delete attendance
    function deleteAttendance(attendanceId) {
        // Confirm deletion
        if (!confirm("Are you sure you want to delete this attendance record?")) {
            return;
        }

        // Send delete request
        $.ajax({
            url: `${BASE_URL}/delete/${attendanceId}`,
            type: "DELETE",
            success: function(response) {
                // Reload data
                loadAttendanceData();

                // Show success message
                alert("Attendance deleted successfully!");
            },
            error: function(xhr, status, error) {
                console.error("Error deleting attendance:", error);
                alert("Failed to delete attendance. Please try again.");
            }
        });
    }

    // Initialize date picker with today's date
    const today = new Date().toISOString().split('T')[0];
    $("#attendanceDate").val(today);
    $("#modalAttendanceDate").val(today);
});