scp -i "C:\Users\juh22\OneDrive\문서\Github\GIT2021-working\myworkspace.pem" -r ./build/libs/*.jar ubuntu@ec2-15-165-17-21.ap-northeast-2.compute.amazonaws.com:/home/ubuntu/app/myworkspace
scp -i "C:\Users\juh22\OneDrive\문서\Github\GIT2021-working\myworkspace.pem" -r ./run.sh ubuntu@ec2-15-165-17-21.ap-northeast-2.compute.amazonaws.com:/home/ubuntu/app/myworkspace
ssh -i "C:\Users\juh22\OneDrive\문서\Github\GIT2021-working\myworkspace.pem" ubuntu@ec2-15-165-17-21.ap-northeast-2.compute.amazonaws.com "sudo chmod 777 /home/ubuntu/app/myworkspace/run.sh"
ssh -i "C:\Users\juh22\OneDrive\문서\Github\GIT2021-working\myworkspace.pem" ubuntu@ec2-15-165-17-21.ap-northeast-2.compute.amazonaws.com "cd /home/ubuntu/app/myworkspace; ./run.sh myworkspace"