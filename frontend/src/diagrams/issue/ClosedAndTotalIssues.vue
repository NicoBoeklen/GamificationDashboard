<template>
  <div>
    <p v-if="!loaded" style="margin-left: 1em">Loading
      <v-progress-circular indeterminate color="white" size="24"></v-progress-circular>
    </p>
    <canvas id="ClosedTotalIssuesChart" style="padding: 1em"></canvas>
  </div>
</template>

<script>
import Chart from 'chart.js/auto';
import {fetchIssues} from '../../objects/issues';
import {onMounted, ref} from "vue";

function convertLocalDateTimeToLocalDate(weekString) {
  // Umwandlung des Strings in ein Date-Objekt
  const dateObj = new Date(weekString);
  const formattedDate = dateObj.toISOString().split('T')[0];
  return formattedDate;
}

let loaded = ref(false);
export default {
  setup() {
    onMounted(async () => {
      loaded.value = false;
      const issues = await fetchIssues();
      loaded.value = true;
      const fixedIssues = issues.amountFixedIssuesTeam;
      const totalIssues = issues.amountTotalIssuesTeam;
      const closedWeeks = issues.weeklyClosedIssues.map(issue => convertLocalDateTimeToLocalDate(issue.week));
      const totalWeeks = issues.weeklyClosedIssues.map(issue => convertLocalDateTimeToLocalDate(issue.week));
      const closedData = issues.weeklyClosedIssues.map(issue => issue.issues);
      const totalData = issues.weeklyTotalIssues.map(issue => issue.issues);
      const labels = Array.from(new Set([...closedWeeks, ...totalWeeks]));
      new Chart(document.getElementById('ClosedTotalIssuesChart'), {
        type: 'line',
        data: {
          labels: labels,
          datasets: [
            {
              label: 'Closed Issues, Total: ' + fixedIssues,
              data: closedData,
              fill: false,
              borderColor: 'rgba(8, 98, 189, 0.5)',
              tension: 0.1,
              pointRadius: 0
            },
            {
              label: 'Total Issues: ' + totalIssues,
              data: totalData,
              fill: false,
              borderColor: 'rgba(230, 98, 100, 0.5)',
              tension: 0.1,
              pointRadius: 0
            }
          ]
        },
        options: {
          responsive: true,
          plugins: {
            tooltip: {
              callbacks: {
                title: function(context) {
                  return 'Week: ' + context[0].label;
                },
                label: function(context) {
                  const issues = context.raw; // Beispiel für Benutzer-spezifische Commits
                  return 'Issues: ' + issues;
                }
              }
            }
          },
          scales: {
            x: {
              display: true,
              title: {
                display: true,
                text: 'Week'
              }
            },
            y: {
              beginAtZero: true,
              display: true,
              title: {
                display: true,
                text: 'Issues'
              }
            }
          }
        }
      });
    })
    return {
      loaded
    }
  }
}
</script>
<style scoped>
</style>
