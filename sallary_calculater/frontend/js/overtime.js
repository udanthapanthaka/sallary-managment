$(document).ready(function() {
    // API base URL
    const API_URL = 'http://localhost:8090/api/v1/overtime';

    // Load all overtime records when page loads
    loadOvertimeRecords();

    // Form submission handler
    $('#overtimeForm').on('submit', function(e) {
        e.preventDefault();
        saveOvertime();
    });

    // Refresh button click handler
    $('#refreshBtn').on('click', function() {
        loadOvertimeRecords();
    });

    // Function to save a new overtime record
    function saveOvertime() {
        const overtimeData = {
            employeeId: parseInt($('#employeeId').val()),
            overtimeHours: parseFloat($('#overtimeHours').val()),
            overtimeRate: parseFloat($('#overtimeRate').val()),
            overtimeDate: $('#overtimeDate').val()
        };

        $.ajax({
            method: "POST",
            contentType: "application/json",
            url: API_URL + "/save",
            data: JSON.stringify(overtimeData),
            success: function(response) {
                alert("Overtime record saved successfully!");
                $('#overtimeForm')[0].reset();
                loadOvertimeRecords();
            },
            error: function(xhr) {
                alert("Error saving overtime record: " + (xhr.responseText || "Unknown error"));
            }
        });
    }

    // Function to load all overtime records
    function loadOvertimeRecords() {
        $.ajax({
            method: "GET",
            url: API_URL + "/getAll",
            success: function(overtimeRecords) {
                let tableBody = $('#overtimeTableBody');
                tableBody.empty(); // clear previous rows

                if (overtimeRecords.length === 0) {
                    tableBody.append('<tr><td colspan="7" class="text-center py-3">No overtime records found</td></tr>');
                    return;
                }

                overtimeRecords.forEach(record => {
                    // Format date
                    const overtimeDate = new Date(record.overtimeDate).toLocaleDateString();

                    // Format currency
                    const rate = parseFloat(record.overtimeRate).toFixed(2);
                    const totalPay = parseFloat(record.totalOvertimePay).toFixed(2);

                    let row = `
                        <tr>
                            <td>${record.id}</td>
                            <td>${record.employeeId}</td>
                            <td>${record.overtimeHours}</td>
                            <td>LKR ${rate}</td>
                            <td>${overtimeDate}</td>
                            <td>LKR ${totalPay}</td>
                            <td>
                                <button class="btn btn-sm btn-warning edit-btn" onclick="editOvertime('${record.id}')">
                                    <i class="fas fa-edit"></i>
                                </button>
                                <button class="btn btn-sm btn-danger delete-btn" onclick="deleteOvertime('${record.id}')">
                                    <i class="fas fa-trash"></i>
                                </button>
                            </td>
                        </tr>
                    `;
                    tableBody.append(row);
                });
            },
            error: function() {
                alert("Failed to load overtime records");
            }
        });
    }

    // Make functions available globally
    window.editOvertime = function(id) {
        $.ajax({
            method: "GET",
            url: API_URL + "/" + id,
            success: function(overtime) {
                // Populate form with overtime data
                $('#employeeId').val(overtime.employeeId);
                $('#overtimeHours').val(overtime.overtimeHours);
                $('#overtimeRate').val(overtime.overtimeRate);

                // Format date for input field (YYYY-MM-DD)
                const overtimeDate = new Date(overtime.overtimeDate);
                const formattedDate = overtimeDate.toISOString().split('T')[0];
                $('#overtimeDate').val(formattedDate);

                // Change form submission to update instead of save
                $('#overtimeForm').off('submit').on('submit', function(e) {
                    e.preventDefault();
                    updateOvertime(id);
                });

                // Change button text
                $('#overtimeForm button[type="submit"]').text('Update Overtime');

                // Scroll to form
                $('html, body').animate({
                    scrollTop: $("#overtimeForm").offset().top - 100
                }, 200);
            },
            error: function() {
                alert("Failed to load overtime details");
            }
        });
    };

    // Function to update overtime
    function updateOvertime(id) {
        const overtimeData = {
            id: id,
            employeeId: parseInt($('#employeeId').val()),
            overtimeHours: parseFloat($('#overtimeHours').val()),
            overtimeRate: parseFloat($('#overtimeRate').val()),
            overtimeDate: $('#overtimeDate').val()
        };

        $.ajax({
            method: "PUT",
            contentType: "application/json",
            url: API_URL + "/update",
            data: JSON.stringify(overtimeData),
            success: function() {
                alert("Overtime record updated successfully!");

                // Reset form and rebind original submit handler
                $('#overtimeForm')[0].reset();
                $('#overtimeForm').off('submit').on('submit', function(e) {
                    e.preventDefault();
                    saveOvertime();
                });

                // Reset button text
                $('#overtimeForm button[type="submit"]').text('Save Overtime');

                loadOvertimeRecords();
            },
            error: function(xhr) {
                alert("Error updating overtime record: " + (xhr.responseText || "Unknown error"));
            }
        });
    }

    // Function to delete overtime
    window.deleteOvertime = function(id) {
        if (confirm("Are you sure you want to delete this overtime record?")) {
            $.ajax({
                method: "DELETE",
                url: API_URL + "/delete/" + id,
                success: function() {
                    alert("Overtime record deleted successfully");
                    loadOvertimeRecords();
                },
                error: function() {
                    alert("Failed to delete overtime record");
                }
            });
        }
    };
});