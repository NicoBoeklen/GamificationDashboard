<template>
  <div>
    <canvas id="commitChart" style="padding: 1em"></canvas>
  </div>
</template>

<script>
import Chart from 'chart.js/auto';
import { fetchCommits } from '../../objects/commits';

export default {
  async mounted() {
    const commits = (await fetchCommits());
    const commitCount = commits.commitCountUser;
    const labels = commits.commitsUser.map(commit => commit.week);
    const data = commits.commitsUser.map(commit => commit.totalCommits);

    new Chart(document.getElementById('commitChart'), {
      type: 'bar',
      data: {
        labels: labels,
        datasets: [
          {
            label: 'Commits per Week, Total: ' + commitCount,
            data: data,
            fill: false,
            backgroundColor: 'rgba(8, 98, 189, 0.5)',
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
              text: 'Commits per Week'
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


