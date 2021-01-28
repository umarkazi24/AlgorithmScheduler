
package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Random;

//Driver class
public class Main
{
    public static final Integer X = 20; // Number of processes
    public static final Integer CPU_NUM = 2;	// keeps count of cpu 
    public static final Integer SWAP_CONTEXT = 15;
    public static Integer PART2 = 0;

    public static void main(String[] args)
    {
        // Check input
        if(args.length > 0 && args[0].equals("-PART2"))
        {
            PART2 = 1;
        }

        // Use ExponentialRandom object for random times
        ExponentialRandom expRand = new ExponentialRandom();
        // use via expRand.nextInt()

        // Creates processes
        Queue<Process> allProcesses = new PriorityQueue<Process>(X, new Comparator<Process>() {
            public int compare(Process left, Process right) {
                if(left.getTimeRequested() < right.getTimeRequested()) return -1;
                else if(left.getTimeRequested() == right.getTimeRequested()) return 0;
                return 1;
            }
        });
        Random random = new Random();
        for(Integer i = 0; i < X; ++i)
        {
            Integer time = random.nextInt(350) + 50;
            Integer priority = random.nextInt(5);
            Integer requestedAt = 0;
            if(i > X/10)
            {
                requestedAt = expRand.nextInt();
            }
            Process process = new Process(i, time, priority, requestedAt);
            allProcesses.add(process);
        }

        // Create schedulers
        List<Scheduler> schedulers = new ArrayList<Scheduler>();

        // Add schedulers to the list
        schedulers.addAll(Arrays.asList(new FCFS(), new SPN(), (new SPN()).enablePreemption(), new RR(), new HRRN()));



        // Run schedulers
        for(Scheduler scheduler : schedulers)
        {
            Queue<Process> currentProcesses = new PriorityQueue<Process>(allProcesses);
            if(PART2 == 0)
            {
                scheduler.addProcesses(currentProcesses);
            }
            else
            {
                addProcessesRequestedNow(allProcesses, currentProcesses, scheduler, 0);
            }
            System.out.println("Using scheduler: " + scheduler.getName());
            while(scheduler.hasUnfinishedProcesses() || (PART2 == 1 && currentProcesses.size() > 0))
            {
                scheduler.tick();
                if(PART2 == 1)
                {
                    addProcessesRequestedNow(allProcesses, currentProcesses, scheduler, scheduler.getTime());
                }
            
            }
            System.out.println();

            // Print final results
            scheduler.printResults();

            for(Process process : allProcesses)
            {
                process.reset();
            }
       
        }

    }

    public static void addProcessesRequestedNow(Queue<Process> allProcesses, Queue<Process> currentProcesses, Scheduler scheduler, Integer time)
    {
        Process nextProcess = currentProcesses.peek();
        if(nextProcess == null) return;
        while(nextProcess.getTimeRequested() <= time)
        {
            nextProcess = currentProcesses.remove();
            scheduler.addProcess(nextProcess);
            nextProcess = currentProcesses.peek();
            if(nextProcess == null) break;
        }
    }
    
}
