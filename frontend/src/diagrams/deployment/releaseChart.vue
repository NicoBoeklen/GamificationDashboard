<template>
  <div>
    <p v-if="releasesCount === 0" style="margin-left: 1em; margin-top: 0.5em;margin-bottom: -1.8em">No Releases</p>
    <canvas id="releaseChart"
            style="padding-bottom: 1em; padding-left: 1em; padding-right: 1em; margin-top: 0;"></canvas>
  </div>
</template>

<script>
import Chart from 'chart.js/auto';
import {fetchReleases} from '../../objects/releases.ts';

export default {
  data() {
    return {
      releasesCount: 0,
    };
  },
  async mounted() {
    const releases = await fetchReleases();
    this.releasesCount = releases.numberOfReleases;
    if (this.releasesCount === 0) {
      return;
    }
    const latestReleases = releases.releaseList.slice(0, 4); // Die neuesten 4 Releases
    const labels = latestReleases.map(release => release.tag_name);
    const publishedDates = latestReleases.map(release => release.published_at);

    const data = {
      labels: labels,
      datasets: [{
        label: 'Release Activity',
        data: latestReleases.map(() => 1),
        borderColor: 'rgb(0,0,0)',
        borderWidth: 2,
        pointRadius: 50,
        pointHoverRadius: 65,
        pointStyle: 'circle',
        pointBorderColor: 'rgb(0,0,0)',
        pointHoverBackgroundColor: 'rgba(255, 255, 255, 1)',
        pointHoverBorderColor: 'rgb(0,0,0)',
        lineTension: 0.2,
        spanGaps: true,
      }]
    };

    new Chart(document.getElementById('releaseChart'), {
      type: 'line',
      data: data,
      options: {
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
          legend: {
            display: false,
          },
          tooltip: {
            callbacks: {
              title: function (tooltipItem) {
                return tooltipItem[0].label;
              },
              label: function (tooltipItem) {
                const index = tooltipItem.dataIndex;
                return `Released at: ${publishedDates[index]}`;
              }
            }
          }
        },
        scales: {
          y: {
            display: false,
          }
        }
      }
    });
  }
}
</script>

<style scoped>
#releaseChart {
  min-height: 200px;
  max-height: 19.5em;
}
</style>
