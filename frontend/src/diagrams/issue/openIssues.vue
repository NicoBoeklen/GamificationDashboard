<template>
  <div>
    <p v-if="!loaded" style="margin-left: 1em">Loading
      <v-progress-circular indeterminate color="white" size="24"></v-progress-circular>
    </p>
    <div class="chart-container"><canvas id="openIssuesChart" style="padding: 1em"></canvas></div>
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
      const labels = issues.weeklyOpenIssues.map(issue => convertLocalDateTimeToLocalDate(issue.week));
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
              borderColor: 'rgba(8, 98, 189, 0.5)',
              tension: 0.1,
              pointRadius: 0
            }
          ]
        },
        options: {
          responsive: true,
          plugins: {
            tooltip: {
              mode: 'index',
              intersect: false
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
              display: true,
              beginAtZero: true,
              title: {
                display: true,
                text: 'Open Issues'
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
#openIssuesChart {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}
</style>
