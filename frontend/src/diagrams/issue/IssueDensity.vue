<template>
  <div>
    <canvas id="issueDensity" style="padding: 1em"></canvas>
  </div>
</template>

<script>
import Chart from 'chart.js/auto';
import { fetchIssues } from '../../objects/issues';
function convertLocalDateTimeToLocalDate(weekString) {
  // Umwandlung des Strings in ein Date-Objekt
  const dateObj = new Date(weekString);
  const formattedDate = dateObj.toISOString().split('T')[0];

  return formattedDate;
}
export default {
  async mounted() {
    const issues = await fetchIssues();
    const labels = issues.issuesPer1000LoC.map(issue => convertLocalDateTimeToLocalDate(issue.week));
    const data = issues.issuesPer1000LoC.map(issue => issue.issueDensity);
    new Chart(document.getElementById('issueDensity'), {
      type: 'line',
      data: {
        labels: labels,
        datasets: [
          {
            label: 'Open Issues',
            data: data,
            fill: false,
            borderColor: 'rgba(8, 98, 189, 0.5)',
            tension: 0.1,
            pointRadius: 0
          }
        ]
      },
      options: {
        responsive: true,
        scales: {
          x: {
            display: true,
            title: {
              display: true,
              text: 'Week'
            }
          },
          y: {
            display: true,
            title: {
              display: true,
              text: 'Open Issues'
            }
          }
        }
      }
    });
  }
}
</script>
<style scoped>
</style>
