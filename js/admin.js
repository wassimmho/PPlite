const user = JSON.parse(localStorage.getItem('user'));
    /*if (!user || user.role !== 'admin') {
      window.location.href = 'login.html';
    }*/

    const matchContainer = document.getElementById('matchStats');

    function loadMatches() {
      matchContainer.innerHTML = '';
      fetch('http://localhost:8080/api/matches')
        .then(res => res.json())
        .then(matches => {
          matches.forEach(match => {
            fetch(`http://localhost:8080/api/tickets/byMatch/${match.id}`)
              .then(res => res.json())
              .then(tickets => {
                const total = tickets.length;
                const sold = tickets.filter(t => t.statut === 'vendu').length;
                const available = total - sold;

                const card = document.createElement('div');
                card.className = 'card mb-3 shadow';
                card.innerHTML = `
                  <div class="card-body">
                    <h5 class="card-title">${match.equipe1} vs ${match.equipe2}</h5>
                    <p class="card-text">Date: ${match.date}, Heure: ${match.heure}, Lieu: ${match.lieu}</p>
                    <p class="card-text">
                      🎟️ Billets vendus: <strong>${sold}</strong><br>
                      🪑 Billets restants: <strong>${available}</strong><br>
                      📦 Total billets: <strong>${total}</strong>
                    </p>
                  </div>
                `;
                matchContainer.appendChild(card);
              });
          });
        });
    }

    loadMatches();

    // Handle match creation
    document.getElementById('createMatchForm').addEventListener('submit', function (e) {
      e.preventDefault();

      const match = {
        equipe1: document.getElementById('equipe1').value,
        equipe2: document.getElementById('equipe2').value,
        date: document.getElementById('date').value,
        heure: document.getElementById('heure').value,
        lieu: document.getElementById('lieu').value,
        capacite: parseInt(document.getElementById('capacite').value)
      };

      fetch('http://localhost:8080/api/matches', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(match)
      })
      .then(res => res.json())
      .then(() => {
        document.getElementById('matchCreated').textContent = 'Match ajouté avec succès !';
        document.getElementById('createMatchForm').reset();
        loadMatches(); // Refresh match list
      });
    });