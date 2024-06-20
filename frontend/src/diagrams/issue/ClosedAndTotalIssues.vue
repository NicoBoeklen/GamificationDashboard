<template>
  <div>
    <canvas id="ClosedTotalIssuesChart" style="padding: 1em"></canvas>
  </div>
</template>

<script>
import Chart from 'chart.js/auto';
import { fetchIssues } from '../../objects/issues';

export default {
  async mounted() {
    const issues = await fetchIssues();

    const closedWeeks = issues.weeklyClosedIssues.map(issue => issue.week);
    const totalWeeks = issues.weeklyClosedIssues.map(issue => issue.week);
    const closedData = issues.weeklyClosedIssues.map(issue => issue.issues);
    const totalData = issues.weeklyTotalIssues.map(issue => issue.issues);
    const labels = Array.from(new Set([...closedWeeks, ...totalWeeks]));
    new Chart(document.getElementById('ClosedTotalIssuesChart'), {
      type: 'line',
      data: {
        labels: labels,
        datasets: [
          {
            label: 'Closed Issues',
            data: closedData,
            fill: false,
            borderColor: 'rgba(8, 98, 189, 0.5)',
            tension: 0.1,
            pointRadius: 0
          },
          {
            label: 'Total Issues',
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
              text: 'Issues'
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
