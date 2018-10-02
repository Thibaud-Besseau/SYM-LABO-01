# SYM-LABO-01

#### Comment organiser les textes pour obtenir une application multi-langues (français, allemand, italien, langue par défaut : anglais) ? Que se passe-t-il si une traduction est manquante dans la langue par défaut ou dans une langue supplémentaire ?

Afin d'organiser les messages de manières optimales, il faut les regrouper dans des fichiers xml dans le répertoire res/values-`(code de la langue)` (par ex: fr pour le français). Ainsi, il est possible de changer un texte pour toute l'application en une seule opération mais aussi de gérer plusieurs langues.
En effet, Android va choisir le fichier xml qui est contenu dans le dossier ayant la meme valeur locale que celle de l'appareil. 
Si aucun dossier value ne possède le meme paramètre local que l'appareil, alors Android utilisera par défaut le fichier strings.xml contenue dans le dossier `value`.

Donc si on veut faire une application multi-langues avec l'anglais comme langue par défaut, on mettra les messages en anglais dans le fichier `/res/values/strings.xml` et on créra un fichier string.xml par langue supplémentaire que l'on veut supporter. On placera chacun de ces fichiers dans le dossier `/res/values-(code de la langue)`

[Documentations](https://developer.android.com/training/basics/supporting-devices/languages)

#### Dans l’exemple fourni, sur le dialogue pop-up, nous affichons l’icône android.R.drawable.ic_dialog_alert, disponible dans le SDK Android mais qui n’est pas très bien adapté visuellement à notre utilisation. Nous souhaitons la remplacer avec notre propre icône, veuillez indiquer comment procéder. Dans quel(s) dossier(s) devons-nous ajouter cette image ? Décrivez brièvement la logique derrière la gestion des ressources de type « image » sur Android.

Si nous souhaitons modifier cette icone, il faut suivre la procédure suivante:

1. Télécharger l'icone désirée ([Google propose sa propre collection](https://material.io/tools/icons/?style=baseline))
2. La placer dans le dossier `drawable` du projet
3. Editer la propriété `android:icon=”@drawable/icon-name“` dans le android manifest

