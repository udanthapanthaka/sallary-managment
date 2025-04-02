async function validateLogin() {
  let username = document.getElementById('username').value;
  let password = document.getElementById('password').value;

  if (!username || !password) {
    alert("Please enter both username and password.");
    return;
  }

  let response = await fetch('http://localhost:8080/api/users/login', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ username, password })
  });

  if (response.ok) {
    let data = await response.json();
    alert("Login Successful!");
    localStorage.setItem("user", JSON.stringify(data)); // Store user data in localStorage
    window.location.href = "dashboard.html"; // Redirect to dashboard after login
  } else {
    alert("Invalid Username or Password!");
  }
}
