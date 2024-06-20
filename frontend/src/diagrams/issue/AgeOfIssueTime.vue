<template>
  <div>
    <canvas id="ageOfIssueTime" style="padding: 1em"></canvas>
  </div>
</template>

<script>
import Chart from 'chart.js/auto';
import { fetchIssues } from '../../objects/issues';

export default {
  async mounted() {
    const issues = await fetchIssues();
    const labels = ['More than a month', 'Between a week and a month','Less than 7 days'];
    const data = [
      issues.amountOpenIssuesMoreThanAMonth,
      issues.amountOpenIssuesBetweenWeekAndMonth,
      issues.amountOpenIssuesLessSevenDays,
    ];
    new Chart(document.getElementById('ageOfIssueTime'), {
      type: 'bar',
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
#openIssuesChart {
  min-height: 100px;
}
</style>
