<template>
  <div>
    <canvas id="openIssuesChart"></canvas>
  </div>
</template>

<script>
import Chart from 'chart.js/auto';
import { fetchIssues } from '../../objects/issues';

export default {
  async mounted() {
    const issues = await fetchIssues();
    const labels = issues.weeklyOpenIssues.map(issue => issue.week);
    const data = issues.weeklyOpenIssues.map(issue => issue.issues);

    new Chart(document.getElementById('openIssuesChart'), {
      type: 'line',
      data: {
        labels: labels,
        datasets: [
          {
            label: 'Open Issues',
            data: data,
            fill: false,
            borderColor: 'rgb(75, 192, 192)',
            tension: 0.1
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
