document.getElementById('loginForm').addEventListener('submit', function(e) {
  e.preventDefault();
  
  // Get form values
  const email = document.getElementById('email').value;
  const password = document.getElementById('password').value; // Assuming you've added the password field
  
  console.log('Attempting login with email:', email);
  
  // Show loading state
  const errorElement = document.getElementById('error');
  errorElement.innerText = "Connexion en cours...";
  errorElement.className = "mt-3 text-info";
  
  // Prepare request data
  const requestData = password 
    ? { email: email, motDePasse: password }
    : { email: email };
  
  console.log('Request data:', requestData);
  
  // Make the API call
  fetch('http://localhost:8080/api/login', {
    method: 'POST',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    body: new URLSearchParams(requestData)
  })
  .then(res => {
    // Log response status and headers
    console.log('Response status:', res.status);
    console.log('Response status text:', res.statusText);
    console.log('Response headers:', {
      'content-type': res.headers.get('content-type'),
      'content-length': res.headers.get('content-length')
    });
    
    // Check if response is OK
    if (!res.ok) {
      console.error('Response not OK:', res.status, res.statusText);
      throw new Error(`HTTP error! Status: ${res.status}`);
    }
    
    // Try to parse JSON
    return res.json().catch(err => {
      console.error('JSON parsing error:', err);
      console.log('Response text:', res.text()); // Log raw response
      throw new Error('Invalid JSON response');
    });
  })
  .then(data => {
    // Log the response data
    console.log('Response data:', data);
    
    if (data && data.id) {
      console.log('Login successful for user ID:', data.id);
      localStorage.setItem('user', JSON.stringify(data));
      window.location.href = 'index.html';
    } else {
      console.error('Login failed: Invalid user data received');
      errorElement.innerText = "Utilisateur non trouvé.";
      errorElement.className = "mt-3 text-danger";
    }
  })
  .catch(error => {
    // Detailed error logging
    console.error('Login error:', error);
    console.error('Error name:', error.name);
    console.error('Error message:', error.message);
    console.error('Error stack:', error.stack);
    
    // Show user-friendly error message
    errorElement.innerText = "Erreur de connexion au serveur: " + error.message;
    errorElement.className = "mt-3 text-danger";
    
    // Additional network diagnostics
    checkServerConnection();
  });
});

// Function to check if the server is reachable
function checkServerConnection() {
  console.log('Checking server connection...');
  
  // Simple HEAD request to check if server is up
  fetch('http://localhost:8080/api', { 
    method: 'HEAD',
    cache: 'no-cache'
  })
  .then(res => {
    console.log('Server is reachable. Status:', res.status);
  })
  .catch(error => {
    console.error('Server connection test failed:', error.message);
    document.getElementById('error').innerText = 
      "Le serveur semble inaccessible. Vérifiez que l'application backend est en cours d'exécution.";
  });
}

// Log initial page load
console.log('Login page loaded at:', new Date().toISOString());
console.log('Browser information:', navigator.userAgent);