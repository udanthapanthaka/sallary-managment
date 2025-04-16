$(document).ready(function () {
  // Load departments on page load
  loadAllDepartments();

  // Bind save button click event
  $('#departmentForm').on('submit', function (e) {
    e.preventDefault();
    saveDepartment();
  });
});

// Save or update department
function saveDepartment() {
  let name = $('#departmentName').val();
  let id = $('#departmentId').val();

  $.ajax({
    method: "POST",
    contentType: "application/json",
    url: "http://localhost:8081/api/v1/departments/save",
    async: true,
    data: JSON.stringify({
      id: id,
      departmentName: name
    }),
    success: function () {
      alert("Department saved!");
      resetForm();
      loadAllDepartments();
    },
    error: function () {
      alert("Error saving department");
    }
  });
}

// Load all departments into table
function loadAllDepartments() {
  $.ajax({
    method: "GET",
    url: "http://localhost:8081/api/v1/departments/getAll",
    success: function (departments) {
      let tableBody = $('#departmentTableBody');
      tableBody.empty(); // clear previous rows

      departments.forEach(dept => {
        let row = `
          <tr>
            <td>${dept.id}</td>
            <td>${dept.departmentName}</td>
            <td>
              <button class="btn btn-sm btn-warning" onclick="editDepartment(${dept.id}, '${dept.departmentName}')">Edit</button>
              <button class="btn btn-sm btn-danger" onclick="deleteDepartment(${dept.id})">Delete</button>
            </td>
          </tr>
        `;
        tableBody.append(row);
      });
    },
    error: function () {
      alert("Failed to load departments");
    }
  });
}

// Set form for editing
function editDepartment(id, name) {
  $('#departmentId').val(id);
  $('#departmentName').val(name);
}

// Delete department
function deleteDepartment(id) {
  if (confirm("Are you sure you want to delete this department?")) {
    $.ajax({
      method: "DELETE",
      url: `http://localhost:8081/api/v1/departments/delete/${id}`,
      success: function () {
        alert("Department deleted");
        loadAllDepartments();
      },
      error: function () {
        alert("Failed to delete department");
      }
    });
  }
}

// Clear form
function resetForm() {
  $('#departmentId').val('');
  $('#departmentName').val('');
}
