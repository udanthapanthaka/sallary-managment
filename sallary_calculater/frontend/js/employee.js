const apiUrl = "http://localhost:8080/api/employees"; // Adjust API endpoint

// Load employees on page load
document.addEventListener("DOMContentLoaded", fetchEmployees);

async function fetchEmployees() {
  try {
    const response = await fetch(apiUrl);
    const employees = await response.json();
    populateEmployeeTable(employees);
  } catch (error) {
    console.error("Error fetching employees:", error);
  }
}

function populateEmployeeTable(employees) {
  const tableBody = document.getElementById("employeeTableBody");
  tableBody.innerHTML = "";

  employees.forEach(emp => {
    tableBody.innerHTML += `
            <tr>
                <td>${emp.employeeId}</td>
                <td>${emp.name}</td>
                <td>${emp.role}</td>
                <td>${emp.email}</td>
                <td>${emp.phone}</td>
                <td>${emp.salary}</td>
                <td>
                    <button class="btn btn-success btn-sm" onclick="editEmployee('${emp.employeeId}')">Edit</button>
                    <button class="btn btn-danger btn-sm" onclick="deleteEmployee('${emp.employeeId}')">Delete</button>
                </td>
            </tr>
        `;
  });
}

// Handle form submission
document.getElementById("employeeForm").addEventListener("submit", async function(event) {
  event.preventDefault();

  const employee = {
    employeeId: document.getElementById("employeeId").value,
    name: document.getElementById("employeeName").value,
    role: document.getElementById("employeeRole").value,
    email: document.getElementById("employeeEmail").value,
    phone: document.getElementById("employeePhone").value,
    salary: parseFloat(document.getElementById("employeeSalary").value)
  };

  const isEditMode = document.getElementById("editMode").value === "true";

  const method = isEditMode ? "PUT" : "POST";
  const url = isEditMode ? `${apiUrl}/${employee.employeeId}` : apiUrl;

  try {
    await fetch(url, {
      method: method,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(employee)
    });

    alert(`Employee ${isEditMode ? "updated" : "added"} successfully!`);
    fetchEmployees();
    bootstrap.Modal.getInstance(document.getElementById("employeeModal")).hide();
  } catch (error) {
    console.error("Error saving employee:", error);
  }
});

// Edit Employee
async function editEmployee(employeeId) {
  try {
    const response = await fetch(`${apiUrl}/${employeeId}`);
    const employee = await response.json();

    document.getElementById("employeeId").value = employee.employeeId;
    document.getElementById("employeeName").value = employee.name;
    document.getElementById("employeeRole").value = employee.role;
    document.getElementById("employeeEmail").value = employee.email;
    document.getElementById("employeePhone").value = employee.phone;
    document.getElementById("employeeSalary").value = employee.salary;
    document.getElementById("editMode").value = "true";

    new bootstrap.Modal(document.getElementById("employeeModal")).show();
  } catch (error) {
    console.error("Error fetching employee:", error);
  }
}

// Delete Employee
async function deleteEmployee(employeeId) {
  if (confirm("Are you sure you want to delete this employee?")) {
    try {
      await fetch(`${apiUrl}/${employeeId}`, { method: "DELETE" });
      fetchEmployees();
    } catch (error) {
      console.error("Error deleting employee:", error);
    }
  }
}
