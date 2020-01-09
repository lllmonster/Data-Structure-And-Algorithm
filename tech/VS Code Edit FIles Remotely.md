## Edit files in Linux Remotely Via VS Code
1. `CTRL+SHIFT+X` to open the extensions & Install `Remote VSCode` extension  
2. Install `Rmate` in the Linux VM  
    ```bash
    wget -O /usr/local/bin/rmate https://raw.github.com/aurora/rmate/master/rmate
    chmod a+x /usr/local/bin/rmate
    ```
3. Install `SSH` in the Linux VM
    ```bash
    sudo apt install openssh-client
    sudo apt install openssh-server
    vi /etc/ssh/sshd_config ## Make Sure PasswordAuthentication yes
    service ssh restart
    ssh localhost -l $user ## Test
    ```
4. Make Sure Win10 has an SSH Client
    ```bash
    ssh ## Test
    ```
5. Start the rmate Server in VS Code
    `F1` to open the command palette & Type `Remote: Start Server`
6. Connect to the VM using SSH in Terminal
    ```bash
    ssh -R 52698:127.0.0.1:52698 $IPAdress -l $user
    ```
7. Open a remote File
    ```bash
    rmate $FILE
    ```

[Reference](https://www.petri.com/how-to-edit-linux-files-remotely-in-windows-using-visual-studio-code)