## XML External Entity (XXE) Attacks

XXE is based on **Server Side Request Forgery**.

This type of attack abuses a widely available but rarely used feature of XML parsers. Using XXE, an attacker is able to cause Denial of Service as well as access local and remote content and services. In some cases, XXE may even enable port scanning and lead to remote code execution.

XXE can only be used to obtain files or responses that contain valid XML; XXE cannot be used to obtain any binary files