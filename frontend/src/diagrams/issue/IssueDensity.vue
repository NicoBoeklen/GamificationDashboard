<template>
  <div>
    <p v-if="!loaded" style="margin-left: 1em">Loading
      <v-progress-circular indeterminate color="white" size="24"></v-progress-circular>
    </p>
    <div class="chart-container"><canvas id="issueDensity" style="padding: 1em"></canvas></div>
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
      const labels = issues.issuesPer1000LoC.map(issue => convertLocalDateTimeToLocalDate(issue.week));
      const data = issues.issuesPer1000LoC.map(issue => issue.issueDensity);
      new Chart(document.getElementById('issueDensity'), {
        type: 'line',
        data: {
          labels: labels,
          datasets: [
            {
              label: 'Open Issues / 1000 LoC',
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
          maintainAspectRatio: false,
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
                text: 'Open Issues / 1k LoC'
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
#issueDensity {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}
</style>
