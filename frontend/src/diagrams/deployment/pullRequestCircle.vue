<template>
  <div>
    <canvas id="pullRequestsChart" style="padding-bottom: 1em; padding-left: 1em; padding-right: 1em;"></canvas>
  </div>
</template>

<script>
import Chart from 'chart.js/auto';
import {fetchPullRequests} from "../../objects/pullRequests";

export default {
  async mounted() {
    const pullRequest = (await fetchPullRequests());
    const openPullRequests = pullRequest.openPullRequests;
    const closedPullRequestsLastMonth = pullRequest.closedPullRequestsLastMonth;

    const data = {
      labels: ['Open Pull Requests', 'Closed Pull Requests Last Month'],
      datasets: [{
        label: 'Pull Requests',
        data: [openPullRequests, closedPullRequestsLastMonth],
        backgroundColor: [
          'rgba(8, 98, 189, 0.5)',
          'rgba(230, 98, 100, 0.5)'
        ],
        borderColor: [
          'rgba(8, 98, 189, 0.5)',
          'rgba(230, 98, 100, 0.5)'
        ],
        borderWidth: 1
      }]
    };

    new Chart(document.getElementById('pullRequestsChart'), {
      type: 'pie',
      data: data,
      options: {
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
          legend: {
            position: 'top',
          },
          tooltip: {
            callbacks: {
              label: function (tooltipItem) {
                return `${tooltipItem.label}: ${tooltipItem.raw}`;
              }
            }
          }
        },
        cutout: 90,
      }
    });
  }
}
</script>

<style scoped>
#pullRequestsChart {
  min-height: 100px;
}
</style>
