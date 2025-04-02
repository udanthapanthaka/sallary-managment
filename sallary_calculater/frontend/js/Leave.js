async function fetchLeaves() {
  let response = await fetch('http://localhost:8080/api/leaves');
  let data = await response.json();
  let tbody = document.getElementById('leaveTableBody');
  tbody.innerHTML = '';
  data.forEach(leave => {
    let row = `<tr>
            <td>${leave.leaveId}</td>
            <td>${leave.employeeId}</td>
            <td>${leave.startDate}</td>
            <td>${leave.endDate}</td>
            <td>${leave.leaveType}</td>
            <td>${leave.status}</td>
        </tr>`;
    tbody.innerHTML += row;
  });
}

window.onload = fetchLeaves;
