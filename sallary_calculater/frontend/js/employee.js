// async function loadDepartments() {
//   try {
//     const response = await fetch('/api/v1/departments'); // Replace with your API endpoint
//     const departments = await response.json();
//
//     const select = document.getElementById('department');
//     select.innerHTML = '<option value="" selected disabled>Select Department</option>';
//
//     departments.forEach(dept => {
//       const option = document.createElement('option');
//       option.value = dept.id; // This will be the foreign key value
//       option.textContent = dept.name; // This is what users will see
//       select.appendChild(option);
//     });
//   } catch (error) {
//     console.error('Error loading departments:', error);
//   }
// }
//
// // Call this when modal is shown
// document.getElementById('addEmployeeModal').addEventListener('shown.bs.modal', loadDepartments);
//
// // When saving, the selected value will be the department ID
// document.getElementById('saveEmployeeBtn').addEventListener('click', async function() {
//   const employeeData = {
//     name: document.getElementById('name').value,
//     email: document.getElementById('email').value,
//     phone: document.getElementById('phone').value,
//     hireDate: document.getElementById('hireDate').value,
//     salary: document.getElementById('salary').value,
//     departmentId: document.getElementById('department').value // This is the foreign key
//   };
//
//   // Send data to backend
//   try {
//     const response = await fetch('/api/v1/employees', {
//       method: 'POST',
//       headers: {
//         'Content-Type': 'application/json'
//       },
//       body: JSON.stringify(employeeData)
//     });
//
//     if (response.ok) {
//     } else {
//     }
//   } catch (error) {
//     console.error('Error saving employee:', error);
//   }
// });

// Base API URL
// const API_BASE_URL = 'http://localhost:8081/api/v1';
//
// // DOM Elements
// const employeeTableBody = document.getElementById('employeeTableBody');
// const searchInput = document.getElementById('searchInput');
//
// // Initialize when page loads
// document.addEventListener('DOMContentLoaded', function() {
//   loadAllEmployees();
//   loadDepartments();
//
//   // Set up event listeners
//   document.getElementById('saveEmployeeBtn').addEventListener('click', saveEmployee);
//   document.getElementById('updateEmployeeBtn').addEventListener('click', updateEmployee);
//   searchInput.addEventListener('input', searchEmployees);
// });
//
// // ==================== Employee CRUD Operations ====================
//
// // Load all employees
// async function loadAllEmployees() {
//   try {
//     const response = await fetch(`${API_BASE_URL}/employees/getAll`);
//     const employees = await response.json();
//
//     if (employees.length === 0) {
//       employeeTableBody.innerHTML = '<tr><td colspan="8" class="text-center py-4">No employee data available</td></tr>';
//       return;
//     }
//
//     employeeTableBody.innerHTML = '';
//     employees.forEach(employee => {
//       const row = createEmployeeRow(employee);
//       employeeTableBody.appendChild(row);
//     });
//   } catch (error) {
//     console.error('Error loading employees:', error);
//     showAlert('Error loading employees', 'danger');
//   }
// }
//
// // Create employee table row
// function createEmployeeRow(employee) {
//   const row = document.createElement('tr');
//   row.innerHTML = `
//     <td>${employee.id}</td>
//     <td>${employee.name}</td>
//     <td>${employee.email}</td>
//     <td>${employee.phoneNumber}</td>
//     <td>${employee.department.departmentName}</td>
//     <td>${formatDate(employee.hireDate)}</td>
//     <td>$${employee.salary.toLocaleString()}</td>
//     <td>
//       <button class="btn btn-sm btn-primary me-1 edit-btn" data-id="${employee.id}">
//         <i class="bi bi-pencil"></i>
//       </button>
//       <button class="btn btn-sm btn-danger delete-btn" data-id="${employee.id}">
//         <i class="bi bi-trash"></i>
//       </button>
//     </td>
//   `;
//
//   // Add event listeners to action buttons
//   row.querySelector('.edit-btn').addEventListener('click', () => openEditModal(employee.id));
//   row.querySelector('.delete-btn').addEventListener('click', () => deleteEmployee(employee.id));
//
//   return row;
// }
//
// // Save new employee
// async function saveEmployee() {
//   const employeeData = {
//     name: document.getElementById('name').value,
//     email: document.getElementById('email').value,
//     phoneNumber: document.getElementById('phone').value,
//     hireDate: document.getElementById('hireDate').value,
//     salary: parseFloat(document.getElementById('salary').value),
//     department: {
//       id: document.getElementById('department').value
//     }
//   };
//
//   try {
//     const response = await fetch(`${API_BASE_URL}/employees/save`, {
//       method: 'POST',
//       headers: {
//         'Content-Type': 'application/json'
//       },
//       body: JSON.stringify(employeeData)
//     });
//
//     if (response.ok) {
//       showAlert('Employee saved successfully!', 'success');
//       $('#addEmployeeModal').modal('hide');
//       document.getElementById('employeeForm').reset();
//       loadAllEmployees();
//     } else {
//       const errorData = await response.json();
//       showAlert(`Error saving employee: ${errorData.message || 'Unknown error'}`, 'danger');
//     }
//   } catch (error) {
//     console.error('Error saving employee:', error);
//     showAlert('Failed to save employee. Please try again.', 'danger');
//   }
// }
//
// // Open edit modal with employee data
// async function openEditModal(employeeId) {
//   try {
//     const response = await fetch(`${API_BASE_URL}/employees/getAll`);
//     const employees = await response.json();
//     const employee = employees.find(e => e.id === employeeId);
//
//     if (!employee) {
//       showAlert('Employee not found', 'danger');
//       return;
//     }
//
//     // Populate form fields
//     document.getElementById('editId').value = employee.id;
//     document.getElementById('editName').value = employee.name;
//     document.getElementById('editEmail').value = employee.email;
//     document.getElementById('editPhone').value = employee.phoneNumber;
//     document.getElementById('editHireDate').value = formatDateForInput(employee.hireDate);
//     document.getElementById('editSalary').value = employee.salary;
//
//     // Load departments and select the current one
//     await loadDepartmentsForEdit(employee.department.id);
//
//     // Show modal
//     $('#editEmployeeModal').modal('show');
//   } catch (error) {
//     console.error('Error loading employee data:', error);
//     showAlert('Error loading employee data', 'danger');
//   }
// }
//
// // Update existing employee
// async function updateEmployee() {
//   const employeeData = {
//     id: document.getElementById('editId').value,
//     name: document.getElementById('editName').value,
//     email: document.getElementById('editEmail').value,
//     phoneNumber: document.getElementById('editPhone').value,
//     hireDate: document.getElementById('editHireDate').value,
//     salary: parseFloat(document.getElementById('editSalary').value),
//     department: {
//       id: document.getElementById('editDepartment').value
//     }
//   };
//
//   try {
//     const response = await fetch(`${API_BASE_URL}/employees/update`, {
//       method: 'PUT',
//       headers: {
//         'Content-Type': 'application/json'
//       },
//       body: JSON.stringify(employeeData)
//     });
//
//     if (response.ok) {
//       showAlert('Employee updated successfully!', 'success');
//       $('#editEmployeeModal').modal('hide');
//       loadAllEmployees();
//     } else {
//       const errorData = await response.json();
//       showAlert(`Error updating employee: ${errorData.message || 'Unknown error'}`, 'danger');
//     }
//   } catch (error) {
//     console.error('Error updating employee:', error);
//     showAlert('Failed to update employee. Please try again.', 'danger');
//   }
// }
//
// // Delete employee
// async function deleteEmployee(employeeId) {
//   if (!confirm('Are you sure you want to delete this employee?')) return;
//
//   try {
//     const response = await fetch(`${API_BASE_URL}/employees/delete/${employeeId}`, {
//       method: 'DELETE'
//     });
//
//     if (response.ok) {
//       showAlert('Employee deleted successfully!', 'success');
//       loadAllEmployees();
//     } else {
//       const errorData = await response.json();
//       showAlert(`Error deleting employee: ${errorData.message || 'Unknown error'}`, 'danger');
//     }
//   } catch (error) {
//     console.error('Error deleting employee:', error);
//     showAlert('Failed to delete employee. Please try again.', 'danger');
//   }
// }
//
// // ==================== Department Functions ====================
//
// // Load departments for select boxes
// async function loadDepartments() {
//   try {
//     const response = await fetch(`${API_BASE_URL}/departments`);
//     const departments = await response.json();
//
//     const addSelect = document.getElementById('department');
//     addSelect.innerHTML = '<option value="" selected disabled>Select Department</option>';
//
//     const editSelect = document.getElementById('editDepartment');
//     if (editSelect) {
//       editSelect.innerHTML = '<option value="" selected disabled>Select Department</option>';
//     }
//
//     departments.forEach(dept => {
//       const option = document.createElement('option');
//       option.value = dept.id;
//       option.textContent = dept.departmentName;
//       addSelect.appendChild(option.cloneNode(true));
//
//       if (editSelect) {
//         editSelect.appendChild(option);
//       }
//     });
//   } catch (error) {
//     console.error('Error loading departments:', error);
//     showAlert('Error loading departments', 'danger');
//   }
// }
//
// // Load departments for edit modal with current selection
// async function loadDepartmentsForEdit(currentDepartmentId) {
//   try {
//     const response = await fetch(`${API_BASE_URL}/departments`);
//     const departments = await response.json();
//
//     const select = document.getElementById('editDepartment');
//     select.innerHTML = '<option value="" selected disabled>Select Department</option>';
//
//     departments.forEach(dept => {
//       const option = document.createElement('option');
//       option.value = dept.id;
//       option.textContent = dept.departmentName;
//       if (dept.id == currentDepartmentId) {
//         option.selected = true;
//       }
//       select.appendChild(option);
//     });
//   } catch (error) {
//     console.error('Error loading departments:', error);
//     showAlert('Error loading departments', 'danger');
//   }
// }
//
// // ==================== Helper Functions ====================
//
// // Search employees
// function searchEmployees() {
//   const searchTerm = searchInput.value.toLowerCase();
//   const rows = employeeTableBody.querySelectorAll('tr');
//
//   rows.forEach(row => {
//     const text = row.textContent.toLowerCase();
//     row.style.display = text.includes(searchTerm) ? '' : 'none';
//   });
// }
//
// // Format date for display
// function formatDate(dateString) {
//   const date = new Date(dateString);
//   return date.toLocaleDateString('en-US', {
//     year: 'numeric',
//     month: 'short',
//     day: 'numeric'
//   });
// }
//
// // Format date for input field
// function formatDateForInput(dateString) {
//   const date = new Date(dateString);
//   const year = date.getFullYear();
//   const month = String(date.getMonth() + 1).padStart(2, '0');
//   const day = String(date.getDate()).padStart(2, '0');
//   return `${year}-${month}-${day}`;
// }
//
// // Show alert message
// function showAlert(message, type) {
//   const alertDiv = document.createElement('div');
//   alertDiv.className = `alert alert-${type} alert-dismissible fade show position-fixed top-0 end-0 m-3`;
//   alertDiv.style.zIndex = '1100';
//   alertDiv.innerHTML = `
//     ${message}
//     <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
//   `;
//
//   document.body.appendChild(alertDiv);
//
//   // Auto remove after 5 seconds
//   setTimeout(() => {
//     alertDiv.remove();
//   }, 5000);
// }



const API_BASE_URL = "http://localhost:8080/api/v1/employees";

// Fetch and display all employees
function loadEmployees() {
  fetch(`${API_BASE_URL}/getAll`)
    .then(res => res.json())
    .then(data => {
      const tableBody = document.getElementById("employeeTableBody");
      tableBody.innerHTML = "";

      if (data.length === 0) {
        tableBody.innerHTML = `<tr><td colspan="8" class="text-center py-4">No employee data available</td></tr>`;
        return;
      }

      data.forEach(emp => {
        tableBody.innerHTML += `
          <tr>
            <td>${emp.id}</td>
            <td>${emp.name}</td>
            <td>${emp.email}</td>
            <td>${emp.phoneNumber}</td>
            <td>${emp.department?.id || 'N/A'}</td>
            <td>${emp.hireDate}</td>
            <td>$${emp.salary}</td>
            <td>
              <button class="btn btn-sm btn-warning me-1" onclick='showEditModal(${JSON.stringify(emp)})'>Edit</button>
              <button class="btn btn-sm btn-danger" onclick='deleteEmployee(${emp.id})'>Delete</button>
            </td>
          </tr>`;
      });
    })
    .catch(err => console.error("Failed to load employees:", err));
}

// Add employee
document.getElementById("saveEmployeeBtn").addEventListener("click", () => {
  const employee = {
    name: document.getElementById("name").value,
    email: document.getElementById("email").value,
    phoneNumber: document.getElementById("phone").value,
    hireDate: document.getElementById("hireDate").value,
    salary: parseFloat(document.getElementById("salary").value),
    department: {
      id: parseInt(document.getElementById("department").value)
    }
  };

  fetch(`${API_BASE_URL}/save`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(employee)
  })
    .then(res => {
      if (res.ok) {
        loadEmployees();
        document.getElementById("employeeForm").reset();
        bootstrap.Modal.getInstance(document.getElementById("addEmployeeModal")).hide();
      }
    })
    .catch(err => console.error("Error adding employee:", err));
});

// Show edit modal
function showEditModal(emp) {
  document.getElementById("editId").value = emp.id;
  document.getElementById("editName").value = emp.name;
  document.getElementById("editEmail").value = emp.email;
  document.getElementById("editPhone").value = emp.phoneNumber;
  document.getElementById("editHireDate").value = emp.hireDate;
  document.getElementById("editSalary").value = emp.salary;
  document.getElementById("editDepartment").value = emp.department?.id || '';

  new bootstrap.Modal(document.getElementById("editEmployeeModal")).show();
}

// Update employee
document.getElementById("updateEmployeeBtn").addEventListener("click", () => {
  const updatedEmployee = {
    id: parseInt(document.getElementById("editId").value),
    name: document.getElementById("editName").value,
    email: document.getElementById("editEmail").value,
    phoneNumber: document.getElementById("editPhone").value,
    hireDate: document.getElementById("editHireDate").value,
    salary: parseFloat(document.getElementById("editSalary").value),
    department: {
      id: parseInt(document.getElementById("editDepartment").value)
    }
  };

  fetch(`${API_BASE_URL}/update`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(updatedEmployee)
  })
    .then(res => {
      if (res.ok) {
        loadEmployees();
        bootstrap.Modal.getInstance(document.getElementById("editEmployeeModal")).hide();
      }
    })
    .catch(err => console.error("Error updating employee:", err));
});

// Delete employee
function deleteEmployee(id) {
  if (confirm("Are you sure you want to delete this employee?")) {
    fetch(`${API_BASE_URL}/delete/${id}`, { method: "DELETE" })
      .then(res => {
        if (res.ok) loadEmployees();
      })
      .catch(err => console.error("Error deleting employee:", err));
  }
}

// Initial load
document.addEventListener("DOMContentLoaded", loadEmployees);
