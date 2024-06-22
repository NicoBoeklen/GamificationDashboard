<template>
  <div>
    <p v-if="!loaded" style="margin-left: 1em">Loading
      <v-progress-circular indeterminate color="white" size="24"></v-progress-circular>
    </p>
    <canvas id="commitChart" style="padding-bottom: 1em; padding-left: 1em;padding-right: 1em;"></canvas>
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
          plugins: {
            tooltip: {
              callbacks: {
                title: function (context) {
                  return 'Week: ' + context[0].label;
                },
                label: function (context) {
                  const commitCountUser = context.raw; // Beispiel für Benutzer-spezifische Commits
                  return 'Commits: ' + commitCountUser;
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
                text: 'Commits per Week'
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

</style>


