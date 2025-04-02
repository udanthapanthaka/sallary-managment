function autoFillEmployeeDetails() {
  let employeeId = document.getElementById("employeeId").value;
  let employeeNameField = document.getElementById("employeeName");
  let roleField = document.getElementById("role");

  // Simulated employee data
  let employees = {
  "EMP001": { name: "Udantha Panthaka", role: "Site Engineer" },
  "EMP002": { name: "Saman Kumara", role: "Project Manager" },
  "EMP003": { name: "Ravishka Mendis", role: "Accountant" }
};

  if (employees[employeeId]) {
  employeeNameField.value = employees[employeeId].name;
  roleField.value = employees[employeeId].role;
} else {
  employeeNameField.value = "";
  roleField.value = "";
}
}

  function calculateNetSalary() {
  let earnings = parseFloat(document.getElementById("totalEarnings").value) || 0;
  let deductions = parseFloat(document.getElementById("totalDeductions").value) || 0;
  let netSalary = earnings - deductions;

  document.getElementById("netSalary").value = "LKR " + netSalary.toFixed(2);

  // Add data to the table
  let table = document.getElementById("payrollTableBody");
  let row = table.insertRow();
  row.innerHTML = `
                <td>${Math.floor(Math.random() * 90000) + 10000}</td>
                <td>${document.getElementById("employeeName").value}</td>
                <td>${document.getElementById("role").value}</td>
                <td>${document.getElementById("month").value}</td>
                <td>${document.getElementById("year").value}</td>
                <td>LKR ${earnings.toFixed(2)}</td>
                <td>LKR ${deductions.toFixed(2)}</td>
                <td class="text-success">LKR ${netSalary.toFixed(2)}</td>
            `;
}
