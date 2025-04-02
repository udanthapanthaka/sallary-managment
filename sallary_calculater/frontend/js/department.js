document.addEventListener("DOMContentLoaded", function () {
  loadDepartments();

  document.getElementById("departmentForm").addEventListener("submit", function (event) {
    event.preventDefault();
    saveDepartment();
  });
});

function loadDepartments() {
  fetch("http://localhost:8080/api/v1/departments/getAll")
    .then(response => response.json())
    .then(data => {
      const tableBody = document.getElementById("departmentTableBody");
      tableBody.innerHTML = "";
      data.data.forEach(department => {
        tableBody.innerHTML += `
                    <tr>
                        <td>${department.id}</td>
                        <td>${department.departmentName}</td>
                        <td>
                            <button class="btn btn-warning btn-sm" onclick="editDepartment(${department.id}, '${department.departmentName}')">Edit</button>
                            <button class="btn btn-danger btn-sm" onclick="deleteDepartment(${department.id})">Delete</button>
                        </td>
                    </tr>`;
      });
    });
}

function saveDepartment() {
  const id = document.getElementById("departmentId").value;
  const name = document.getElementById("departmentName").value;

  const department = {
    id: id ? parseInt(id) : null,
    departmentName: name
  };

  const method = id ? "PUT" : "POST";
  const url = id ? "http://localhost:8080/api/v1/departments/update" : "http://localhost:8080/api/v1/departments/save";

  fetch(url, {
    method: method,
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(department)
  }).then(() => {
    document.getElementById("departmentForm").reset();
    document.getElementById("departmentId").value = "";
    loadDepartments();
  });
}

function editDepartment(id, name) {
  document.getElementById("departmentId").value = id;
  document.getElementById("departmentName").value = name;
}

function deleteDepartment(id) {
  fetch(`http://localhost:8080/api/v1/departments/delete/${id}`, { method: "DELETE" })
    .then(() => loadDepartments());
}
