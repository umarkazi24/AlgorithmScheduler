# AlgorithmScheduler

Files included in the archive and their purpose:

Process.java: This class includes a series of overloaded constructors that will be performing
different operations depending on the parameters passed. The process class gives the details
about the process that is currently being executed like getTimeRemaining(), getTimeWaiting(),
getTimeInitiallyWaiting(), getTimeTotal(),  getId(), getPriority(), getTimeRequested(), pause(),
reset() and run().This also sets the true turnaround time which is the time taken to run process
and the total time waiting 

Main.java: This is the driver class that creates processes and schedulers and executes all
the scheduling algorithms. This class contains a queue object for the processes for 
testing purposes. 

Scheduler.java: This is the parent class of the scheduling algorithms and it is kept abstract 
so that its child classes can override some of its functions. It runs and returns processes 
and the names of the scheduling algorithms and prints the information regarding the process
time.

Its child classes are: 

FCFS.java:This class is for the First Come First Serve scheduler which automatically
executes queued requests and processes in order of their arrival. It is the simplest algorithm 
managed by a First in First Out queue. This class extends Scheduler class.

HRRN.java: This class is for the Highest Response Ratio Next scheduler which is 
a scheduler which is done on the basis of an extra parameter called Response Ratio. I implemented this
by input the number of processes, their arrival times and burst times. Then i sorted them according to
their arrival times.

RR.java:This class is the Round Robin Scheduler which is a preemptive process scheduling
algorithm. Each process is provided a fix time to execute, it is called a quantum. Once a process is
executed for a given time period, it is preempted and other process executes for a given time period. 

SPN.java: This class is for the Shortest Process Next also known as Shortest Job First scheduler.
This scheduler selects the waiting process with the smallest execution time. This is a non-preemptive algorithm.

ExponentialRandom: Uses java.lang.Math library to use Math.random() to create random times for use in Main method
for processes.

Each child is inheriring properties from Scheduler and has its own different function accordingly to perfrom scheduling.
There is a file named process, that has the processId and other attributes etc. that are used to adjust the priority etc. of the processes.

Explain how to compile and run your project:

I did all my code in JAVA Eclipse, so to compile this you must have a JDK installed and run the program as usual.



