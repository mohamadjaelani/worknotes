# javanotes

[Use SSH instead of HTTPS]
If you have problems with HTTPS access, you can try pushing to GitHub using the SSH protocol.

You need to generate an SSH key and add it to your GitHub account.
Add an SSH key to GitHub:
Copy the generated public key (~/.ssh/id_rsa.pub).
Log in to GitHub and go to Settings -> SSH and GPG keys -> New SSH keys.
Change the remote warehouse URL to SSH format:
git remote set-url origin git@github.com:{repo_name}/{name}.git
