- Inorder to run playlist application locally we need to pull postgres from docker.

- Then we need a docker-compose file in our root application which specifies both our applications name and
  connection port and also it shows that our application depends on the database image.
  
- Then we also need a postgres properties file and in the URL for the postgres we should give the name of the 
  database instead of localhost.
  
- The last step is to run the "docker-compose up" command.