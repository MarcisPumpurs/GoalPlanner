USE goal_planner;

INSERT INTO users (username, password)
VALUES ("Armands", "pass1"),
       ("Pēteris", "pass2"),
       ("Didzis", "pass3");
       

INSERT INTO sessions(user_id)
VALUES (1),
	   (2),
	   (3);

INSERT INTO status(name)
VALUES ("Jauns"),
	   ("Izpildē"),
       ("Apturēts"),
       ("Atcelts"),
       ("Izpildīts");

INSERT INTO plans(status_id, name)
VALUES (2, "Izveidot GoalPlanner"),
	   (1, "Nomazgāt auto"),
       (3, "Apceļot Ēģipti"),
       (1, "Iemācīties Kotlin"),
       (5, "Pieteikties SDA kursiem"),
       (1, "Aizbraukt slēpot");

INSERT INTO tasks(plan_id, status_id, name)
VALUES (1, 1, "Izveidot internacionalizāciju"),
	   (1, 5, "Izveidot GIT repozitoriju"),
       (1, 2, "Izveidot aplikācijas kodolu"),
       (4, 5, "Iegādāties literatūru"),
       (4, 2, "Izdalīt laika resursus"),
       (6, 1, "Rezervēt vietu uz kalna"),
       (6, 1, "Paņemt darbā brīvdienu");

INSERT INTO subtasks(task_id, status_id, name)
VALUES (1, 2, "Atrast instrukciju, kā veicama internacionalizācija"),
	   (1, 5, "Nozīmēt atbildīgo"),
       (1, 1, "Notestēt atsevišķas funkcijas darbību"),
       (1, 1, "Implementēt aplikācijā");
       
INSERT INTO userplans(plan_id, user_id)
VALUES (1, 3),
	   (2, 1),
	   (3, 2);
