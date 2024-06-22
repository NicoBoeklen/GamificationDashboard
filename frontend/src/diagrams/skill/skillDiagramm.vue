<template>
  <div>
    <canvas id="skillsChart" style="padding: 0em 1em 0em 1em; margin: -3em 0"></canvas>
  </div>
</template>

<script>
import Chart from 'chart.js/auto';
import { fetchSkills } from '../../objects/skills';  // Passe den Pfad entsprechend an

export default {
  async mounted() {
    const skills = await fetchSkills();
    const labels = ['Reviews', 'Fixed Issues', 'Productivity', 'Commits'];
    const data = [
      skills.reviewValueUser,
      skills.fixedIssuesValueUser,
      skills.productivityValueUser,
      skills.commitValueUser
    ];

    new Chart(document.getElementById('skillsChart'), {
      type: 'radar',
      data: {
        labels: labels,
        datasets: [
          {
            label: 'User Skills',
            data: data,
            fill: true,
            backgroundColor: 'rgba(8, 98, 189, 0.2)',
            borderColor: 'rgba(8, 98, 189, 1)',
            pointBackgroundColor: 'rgba(8, 98, 189, 1)',
            pointBorderColor: '#fff',
            pointHoverBackgroundColor: '#fff',
            pointHoverBorderColor: 'rgba(8, 98, 189, 1)'
          }
        ]
      },
      options: {
        responsive: true,
        scales: {
          r: {
            angleLines: {
              display: true
            },
            suggestedMin: 0,
            suggestedMax: 10,
            beginAtZero: true,
            ticks: {
              stepSize: 2
            },
            pointLabels: {
              font: {
                size: 14
              }
            }
          }
        },
        plugins: {
          legend: {
            display: false,
          }
        }
      }
    });
  }
}

</script>

<style scoped>

</style>
