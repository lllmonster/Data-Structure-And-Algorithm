# Network
## Remote Procedure Call (RPC)
[Ref](https://searchapparchitecture.techtarget.com/definition/Remote-Procedure-Call-RPC)  
Remote Procedure Call (RPC) is a protocol that one program can use to request a service from a program located in another computer on a network without having to understand the networks' details.  
RPC is used to call other processes on the remote systems like a local system. A procedure call is also sometimes known as a function call or a subroutine call.  

RPC uses the `client-server` model. The requesting program is a client, and the service-providing program is the server. Like a regular or local procedure call, an RPC is a synchronous operation requiring the requesting program to be suspend until the results of the remote procedure are returned. However, the use of lightweight processors or threads that share the same address space enables multiple RPCs to be performed concurrently.  

**How does RPC work**  
When a remote procedure call is invoked, the calling environment is suspended, the procedure parameters are transferred accross the network to the environment where the procedure is to execute, and the procedure is then executed in that environment.  
When the procedure finishes, the results are transferred back to the calling environment, where execution resumes as if returning from a regualr procedure call.  
