import java.util.*;

class Job {
    char id;
    int deadline;
    int profit;

    public Job(char id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

class JobSequencing {
    public static void findJobSequence(List<Job> jobs) {
        int n = jobs.size();

        // Sort jobs in descending order of profits
        jobs.sort((j1, j2) -> j2.profit - j1.profit);

        // Find the maximum deadline
        int maxDeadline = 0;
        for (Job job : jobs) {
            if (job.deadline > maxDeadline) {
                maxDeadline = job.deadline;
            }
        }

        // Create an array to track the time slots
        char[] result = new char[maxDeadline];
        Arrays.fill(result, '-');

        // Fill the result array with job IDs based on deadlines
        for (Job job : jobs) {
            for (int i = job.deadline - 1; i >= 0; i--) {
                if (result[i] == '-') {
                    result[i] = job.id;
                    break;
                }
            }
        }

        // Display the job sequence and total profit
        int totalProfit = 0;
        System.out.println("Job Sequence:");
        for (char jobId : result) {
            System.out.print(jobId + " ");
            if (jobId != '-') {
                for (Job job : jobs) {
                    if (job.id == jobId) {
                        totalProfit += job.profit;
                        break;
                    }
                }
            }
        }
        System.out.println("\nTotal Profit: " + totalProfit);
    }

    public static void main(String[] args) {
        List<Job> jobs = new ArrayList<>();
        jobs.add(new Job('A', 2, 100));
        jobs.add(new Job('B', 1, 19));
        jobs.add(new Job('C', 2, 27));
        jobs.add(new Job('D', 1, 25));
        jobs.add(new Job('E', 3, 15));

        findJobSequence(jobs);
    }
}
