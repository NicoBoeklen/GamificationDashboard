<template>
  <div>
    <p v-if="!loaded" style="margin-left: 1em">Loading
      <v-progress-circular indeterminate color="white" size="24"></v-progress-circular>
    </p>
    <div class="chart-container"><canvas id="ageOfIssueTime" style="padding: 1em"></canvas></div>
  </div>
</template>

<script>
import Chart from 'chart.js/auto';
import {fetchIssues} from '../../objects/issues';
import {onMounted, ref} from "vue";

let loaded = ref(false);

export default {
  setup() {
    onMounted(async () => {
      loaded.value = false;
      const issues = await fetchIssues();
      loaded.value = true;
      const openIssues = issues.amountOpenIssuesTeam;
      const labels = ['Less than 7 days', 'Between a week and a month', 'More than a month'];
      const data = [
        issues.amountOpenIssuesLessSevenDays,
        issues.amountOpenIssuesBetweenWeekAndMonth,
        issues.amountOpenIssuesMoreThanAMonth
      ];
      new Chart(document.getElementById('ageOfIssueTime'), {
        type: 'bar',
        data: {
          labels: labels,
          datasets: [
            {
              label: 'Open Issues, Total: ' + openIssues,
              data: data,
              fill: false,
              backgroundColor: 'rgba(8, 98, 189, 0.5)',
              tension: 0.1,
              pointRadius: 0
            }
          ]
        },
        options: {
          responsive: true,
          plugins: {
            tooltip: {
              callbacks: {
                title: function(context) {
                  return context[0].label;
                },
                label: function(context) {
                  const issues = context.raw; // Beispiel für Benutzer-spezifische Commits
                  return 'Issues: ' + issues;
                }
              }
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
