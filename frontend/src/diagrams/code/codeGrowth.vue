<template>
  <div>
    <canvas id="codeGrowthChart" style="padding: 1em"></canvas>
  </div>
</template>

<script>
import Chart from 'chart.js/auto';
import { fetchCommits } from '../../objects/commits';

export default {
  async mounted() {
    const commits = (await fetchCommits());
    const labels = commits.codeGrowthList.map(commit => commit.week);
    const data = commits.codeGrowthList.map(commit => commit.totalChanges);

    new Chart(document.getElementById('codeGrowthChart'), {
      type: 'line',
      data: {
        labels: labels,
        datasets: [
          {
            label: 'Total Lines of Code',
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
              text: 'Total Lines of Code'
            }
          }
        }
      }
    });
  }
}
</script>
<style scoped>
#codeGrowthChart {
  min-height: 100px;
}
</style>

