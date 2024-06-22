<template>
  <div>
    <p v-if="!loaded" style="margin-left: 1em">Loading
      <v-progress-circular indeterminate color="white" size="24"></v-progress-circular>
    </p>
    <div class="chart-container"> <canvas id="codeGrowthChart" style="padding-bottom: 1em; padding-left: 1em;padding-right: 1em;"></canvas> </div>
  </div>
</template>

<script>
import Chart from 'chart.js/auto';
import {fetchCommits} from '../../objects/commits';
import {onMounted, ref} from "vue";

let loaded = ref(false);

export default {
  setup() {
    onMounted(async () => {
      loaded.value = false;
      const commits = (await fetchCommits());
      loaded.value = true;
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
          //maintainAspectRatio: false,
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
                text: 'Total Lines of Code'
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
.chart-container {
  position: relative;
  width: 100%;
  padding-bottom: 50%;
  height: 0;
}
#codeGrowthChart {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}
</style>

