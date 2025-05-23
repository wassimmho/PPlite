const matchId = localStorage.getItem('matchId');
    const user = JSON.parse(localStorage.getItem('user'));

    /*if (!matchId || !user) {
      window.location.href = 'login.html';
    }*/

    fetch(`http://localhost:8080/api/tickets/available/${matchId}`)
      .then(res => res.json())
      .then(tickets => {
        const container = document.getElementById('ticketList');
        if (tickets.length === 0) {
          container.innerHTML = '<p>Aucun ticket disponible pour ce match.</p>';
          return;
        }

        tickets.forEach(ticket => {
          const card = document.createElement('div');
          card.className = 'col';
          card.innerHTML = `
            <div class="card h-100 shadow">
              <div class="card-body">
                <h5 class="card-title">Place: ${ticket.numeroPlace}</h5>
                <p class="card-text">Prix: ${ticket.prix} DA</p>
                <button class="btn btn-success" onclick="goToReserve(${ticket.id})">Réserver</button>
              </div>
            </div>
          `;
          container.appendChild(card);
        });
      });

    function reserveTicket(ticketId) {
      fetch(`http://localhost:8080/api/tickets/reserve?ticketId=${ticketId}&utilisateurId=${user.id}`, {
        method: 'POST'
      })
      .then(res => res.json())
      .then(success => {
        if (success) {
          payForTicket(ticketId);
        } else {
          alert('Erreur lors de la réservation du ticket.');
        }
      });
    }

    function payForTicket(ticketId) {
      const payment = {
        idTicket: ticketId,
        idUtilisateur: user.id,
        modePaiement: 'carte',  // you could let user choose later
        statut: ''
      };

      fetch('http://localhost:8080/api/paiement', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payment)
      })
      .then(res => res.json())
      .then(success => {
        if (success) {
          document.getElementById('successMessage').textContent = "Réservation et paiement réussis !";
          setTimeout(() => window.location.href = 'index.html', 2000);
        } else {
          alert('Échec du paiement.');
        }
      });
    }
	
	function goToReserve(ticketId) {
		window.location.href = `reserve.html?ticketId=${ticketId}`;
	}