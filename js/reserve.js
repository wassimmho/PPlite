const user = JSON.parse(localStorage.getItem('user'));
    const ticketId = new URLSearchParams(window.location.search).get('ticketId');

   /* if (!user || !ticketId) {
      window.location.href = 'login.html';
    }*/

    // Load ticket details
    fetch(`http://localhost:8080/api/tickets/${ticketId}`)
      .then(res => res.json())
      .then(ticket => {
        const container = document.getElementById('ticketDetails');
        container.innerHTML = `
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">Place: ${ticket.numeroPlace}</h5>
              <p class="card-text">Prix: ${ticket.prix} DA</p>
              <p class="card-text">Statut: ${ticket.statut}</p>
            </div>
          </div>
        `;
      });

    // Handle payment form
    document.getElementById('paymentForm').addEventListener('submit', function (e) {
      e.preventDefault();
      const mode = document.getElementById('modePaiement').value;

      // Step 1: Reserve the ticket
      fetch(`http://localhost:8080/api/tickets/reserve?ticketId=${ticketId}&utilisateurId=${user.id}`, {
        method: 'POST'
      })
        .then(res => res.json())
        .then(success => {
          if (success) {
            // Step 2: Process payment
            const paiement = {
              idTicket: ticketId,
              idUtilisateur: user.id,
              modePaiement: mode,
              statut: ''
            };

            fetch('http://localhost:8080/api/paiement', {
              method: 'POST',
              headers: { 'Content-Type': 'application/json' },
              body: JSON.stringify(paiement)
            })
              .then(res => res.json())
              .then(paymentSuccess => {
                if (paymentSuccess) {
                  document.getElementById('successMessage').innerText = 'Réservation et paiement effectués avec succès !';
                  setTimeout(() => window.location.href = 'index.html', 2500);
                } else {
                  alert('Échec du paiement.');
                }
              });
          } else {
            alert('La réservation a échoué. Ticket peut-être déjà pris.');
          }
        });
    });