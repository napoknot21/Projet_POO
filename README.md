# Colons de Catane
Ce projet est basé sur le jeu Les Colons de Catane dont vous pouvez trouver les règles complètes sur :https://www.regledujeu.fr/catane/. Il est disponible en application android sous le nom Catan Universe, ou sur un navigateur de PC https://catanuniverse.com/fr/game/ Vous avez probablement déjà passé du temps en soirée ou en ligne sur des jeux de ce type. L’intérêt ici est de vous rendre compte du travail à fournir pour en réaliser les bases. Bien entendu il ne s’agit pas de produire un résultat final de qualité professionnelle comme ceux précédemment cités, mais d’aborder le problème de la conception en compartimentant bien les différents aspects,quitte à les améliorer ensuite.
Page moodle du cours

# Comment lancer le projet ?

Le projet a été conçue pour la version de ```JAVA 17```, on il faudra télécharger ou installer le ```jdk17.0``` pour que le projet marche

## Instaler java17
D'abord on télécharge ```.deb``` package.
```
$ wget https://download.oracle.com/java/17/latest/jdk-17_linux-x64_bin.deb
```
Et enfin, on fait marcher cette commande
```aidl
$ sudo dpkg -i jdk-17_linux-x64_bin.deb
```

## Lancer le projet
On décompresse le fichier ```Projet_CharlyMartin_MartinChampion.zip``` grâce à :
```
$ unzip Projet_CharlyMartin_MartinChampion.zip
```

On le change de répértoire vers src :
```
$ cd src
```
Et enfin, on compile et on le lance avec les commandes:
```aidl
$ javac Launcher.java
$ java Launcher
```

## Lancer le projet depuis une IDE
Si possible (*et fortement recomandé*) essayez de bconstruir et lancer le projet depuis une IDE (par exemple, IntelliJ, NetBeans, Eclipse, etc) car l'installation des jdks est local sur l'IDE et n'a pas besoin de tous les instructions ci-dessus