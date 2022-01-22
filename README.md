# TP1 - Tâches

Dans ce TD, nous allons voir comment les processus sont exécutés quand on utilise Java. On étudie également, comment implémenter en Java une application qui exécute plusieurs tâches en parallèle à l’aide de _threads_.

**Remarque :** Ce TD servira de base à la plupart des autres TD de programmation parallèle. N’hésitez donc pas à poser des questions pour bien comprendre les différents concepts utilisés et essayez d’écrire un code clair et bien commenté.


## Exercice 1: Processus

Dans ce premier exercice, on veut examiner l’exécution des programmes écrits en Java.

  1. Écrivez un programme `Ex1Main` (une classe avec une méthode `main`) qui prend un nom en paramètre et affiche les entiers de 0 à 100 précédés du nom avec un intervalle de 10ms entre chaque affichage.
    Par exemple en exécutant la commande `java Ex1 toto` le programme afficherait :
```
toto: 0
toto: 1
toto: 2
toto: 3
...
toto: 99
```
**Indication :** Vous pouvez utiliser la méthode `java.lang.Thread.sleep(long millis)` pour faire une pause de `millis` millisecondes (regardez la documentation).

  2. Compilez votre programme et exécutez-le 3 fois.
  3. Comment les programmes _.java_ sont-ils exécutés ?
  4. Comment faire pour que les trois programmes soient exécutés en parallèle ?
  5. Comment la concurrence entre les processus est-elle gérée ? Comment est réalisé l’ordonnancement des processus ?


## Exercice 2: _Threads_ avec heritage de la classe `Thread`

On veut maintenant réaliser un programme organisé en 4 tâches exécutées en parallèle :

| Tâche principale (Application) | Tâche T1 | Tâche T2 | Tâche T3 |
|--------------------------------|----------|----------|----------|
| Création et exécution parallèle<br>des tâches T1, T2, T3 | 30 affichages<br>espacés de 100ms | 30 affichages<br>espacés de 200ms | 10 affichages<br>espacés de 500ms |

1. Regardez le code de la classe `Activite` (fournie dans ce projet).
2. Écrivez une classe `Ex2Tache` qui hérite de la classe `java.lang.Thread` avec 3 attributs `int id`, `int pause` et `int nbIterations`.
3. Écrivez la méthode `run()` de la classe `Ex2Tache` qui appelle la méthode `executer` d'une `Activite` initialisée avec les paramètres `id`, `pause` et `nbIterations`. La méthode `run()` doit donc afficher `nbIterations` fois l’identifiant de la tâche et sa trace (cf. classe `Activite`), en faisant une pause d'environ `delai` millisecondes entre deux affichages.
4. Écrivez un programme `Ex2Main` qui exécute en parallèle les trois tâches `T1`, `T2` et `T3` décrites précédemment (il faut créer 3 objets de type `Ex2Tache` et les exécuter).

    **Remarque :** Vérifiez que les tâches sont bien exécutées en parallèle. Si ce n'est pas le cas, cherchez dans la documentation de `Thread` comment exécuter les tâches.
5. Étudiez les méthode `getState`, `isAlive` et `join` de la classe `Thread` (cherchez dans la documentation). Modifiez votre programme pour que le `main()` affiche un message lorsque les trois tâches `T1`, `T2` et `T3` sont terminées.
6. Pour voir l’évolution des threads, afficher les états par la méthode `getState()`
   - après la création des _threads_
   - plus tard, après le lancement des _threads_ (par exemple, 500ms après le lancement des 3 _threads_)
   - après la fin de l’exécution des _threads_ (après le retour des méthodes `join()`)
7. Comment l’ordonnancement des tâches se passe-t-il ?


## Exercice 3: _Threads_ en implémentant l’interface `Runnable`

Nous allons ici refaire l'exercice précédent mais en définissant une classe `Ex3Tache` qui implémente l'interface `Runnable` au lieu d'hériter de la classe `Thread`.

1. Écrivez et testez la classe `Ex3Tache` similaire à `Ex2Tache` mais qui implémente `Runnable`.
2. Écrivez un programme `Ex3Main` semblable à `Ex2Main` mais qui crée et exécute des _threads_ à partir d'objets de type `Ex3Tache`.

**Indication :** pour créer un _thread_ qui exécute une classe implémentant l'interface `Runnable`, vous pouvez utiliser le constructeur `Thread(Runnable runnable)`.


## Exercice 4: Ordonnancement

1. Expliquez ce qui se passe si on exécute les programmes précédents (`Ex2Main` ou `Ex3Main`) après avoir supprimé (ou commenté) les lignes 
```java
try {
   Thread.sleep(delai); // milliseconds
} catch (InterruptedException e) {}
```
dans la classe `Activite`.

2. Même question si l'on remplace les lignes précédemment supprimées par
```java
Thread.yield();
```
(regardez dans la documentation de `Thread`)