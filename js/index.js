const user = JSON.parse(localStorage.getItem('user'));
    /*if (!user) {
      window.location.href = 'login.html';
    }*/

    fetch('http://localhost:8080/api/matches')
      .then(res => res.json())
      .then(matches => {
        const container = document.getElementById('matchList');
        matches.forEach(match => {
          const card = document.createElement('div');
          card.className = 'col';
          card.innerHTML = `
            <div class="card h-100 shadow">
              <div class="card-body">
                <h5 class="card-title">${match.equipe1} vs ${match.equipe2}</h5>
                <p class="card-text">Date: ${match.date} - Heure: ${match.heure}</p>
                <p class="card-text">Lieu: ${match.lieu}</p>
                <button class="btn btn-primary" onclick="viewTickets(${match.id})">Voir les tickets</button>
              </div>
            </div>
          `;
          container.appendChild(card);
        });
      });

    function viewTickets(matchId) {
      localStorage.setItem('matchId', matchId);
      window.location.href = 'tickets.html';
    }