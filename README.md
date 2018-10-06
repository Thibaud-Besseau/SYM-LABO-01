# SYM-LABO-01

## Questions

### 1. Comment organiser les textes pour obtenir une application multi-langues (français, allemand, italien, langue par défaut : anglais) ? Que se passe-t-il si une traduction est manquante dans la langue par défaut ou dans une langue supplémentaire ?

Afin d'organiser les messages de manières optimales, il faut les regrouper dans des fichiers xml dans le répertoire res/values-`(code de la langue)` (par ex: fr pour le français). Ainsi, il est possible de changer un texte pour toute l'application en une seule opération mais aussi de gérer plusieurs langues.
En effet, Android va choisir le fichier xml qui est contenu dans le dossier ayant la meme valeur locale que celle de l'appareil. 
Si aucun dossier value ne possède le meme paramètre local que l'appareil, alors Android utilisera par défaut le fichier strings.xml contenue dans le dossier `value`.

Donc si on veut faire une application multi-langues avec l'anglais comme langue par défaut, on mettra les messages en anglais dans le fichier `/res/values/strings.xml` et on créra un fichier string.xml par langue supplémentaire que l'on veut supporter. On placera chacun de ces fichiers dans le dossier `/res/values-(code de la langue)`

[Documentations](https://developer.android.com/training/basics/supporting-devices/languages)

### 2. Dans l’exemple fourni, sur le dialogue pop-up, nous affichons l’icône android.R.drawable.ic_dialog_alert, disponible dans le SDK Android mais qui n’est pas très bien adapté visuellement à notre utilisation. Nous souhaitons la remplacer avec notre propre icône, veuillez indiquer comment procéder. Dans quel(s) dossier(s) devons-nous ajouter cette image ? Décrivez brièvement la logique derrière la gestion des ressources de type « image » sur Android.

Si nous souhaitons modifier cette icone, il faut suivre la procédure suivante:

1. Télécharger l'icone désirée ([Google propose sa propre collection](https://material.io/tools/icons/?style=baseline))
2. La placer dans le dossier `drawable` du projet
3. Editer la propriété `android:icon=”@drawable/icon-name“` dans le android manifest

### 3. Lorsque le login est réussi, vous êtes censé chaîner une autre Activity en utilisant un Intent. Si je presse le bouton "Back" de l'interface Android, que puis-je constater ? Comment faire pour que l'application se comporte de manière plus logique ? Veuillez discuter de la logique derrière les activités Android.

Si on appuye sur "Back" depuis la seconde activité, on quitte l'application, car l'activité précédente a été terminée en appelant `finish()`. Si on veut revenir sur la première activité, il ne faut pas la terminer au moment de démarrer la deuxième activité.

Android crée par défaut une pile d'activités ("Back Stack"), et le bouton "Back" permet de remonter dans cet historique d'activités. Ce comportement peut être modifié si besoin.

### 4. On pourrait imaginer une situation où cette seconde Activity fournit un résultat (par exemple l’IMEI ou une autre chaîne de caractères) que nous voudrions récupérer dans l'Activity de départ. Comment procéder ?

Il faut utiliser la méthode `startActivityForResult()`, qui permet de retourner un résultat à l'activité appelante via un callback (`onActivityResult()`).

[Documentation](https://developer.android.com/training/basics/intents/result)

### 5. Vous noterez que la méthode `getDeviceId()` du `TelephonyManager`, permettant d’obtenir l’IMEI du téléphone, est dépréciée depuis la version 26 de l’API. Veuillez discuter de ce que cela implique lors du développement et de présenter une façon d’en tenir compte avec un exemple de code.

Cette méthode a été remplacée par la méthode [getImei()](https://developer.android.com/reference/android/telephony/TelephonyManager.html#getImei())

```java
TelephonyManager tm = (TelephonyManager) getSystemService(this.TELEPHONY_SERVICE);
String imei = tm.getImei();
```

### 6. Dans l’activité de login, en plaçant le téléphone (ou l’émulateur) en mode paysage (landscape), nous constatons que les 2 champs de saisie ainsi que le bouton s’étendent sur toute la largeur de l’écran. Veuillez réaliser un layout spécifique au mode paysage qui permet un affichage mieux adapté et indiquer comment faire pour qu’il soit utilisé automatiquement à l’exécution.

Il faut copier le fichier de layout dans un nouveau répertoire `/res/layout-land`, et l'adapter selon les besoins. Il sera alors automatiquement utilisé lorsque le téléphone passera en mode paysage.

### 7. Le layout de l’interface utilisateur de l’activité de login qui vous a été fourni a été réalisé avec un LinearLayout à la racine. Nous vous demandons de réaliser un layout équivalent utilisant cette fois-ci un RelativeLayout.

L'activité de login a été réalisée avec un `RelativeLayout`.

Pour placer les éléments les uns après les autres, nous avons utilisé l'attribut `android:layout_below="@id/..."`.

### 8. Implémenter dans votre code les méthodes onCreate(), onStart(), onResume(), onPause(), onStop(), etc... qui marquent le cycle de vie d'une application Android, et tracez leur exécution dans le logcat. Décrivez brièvement à quelles occasions ces méthodes sont invoquées. Si vous aviez (par exemple) une connexion Bluetooth (ou des connexions bases de données, ou des capteurs activés) ouverte dans votre Activity, que faudrait-il peut-être faire, à votre avis (nous ne vous demandons pas de code ici) ?

[Cycle de vie d'une application Android](https://developer.android.com/reference/android/app/Activity#ActivityLifecycle):

![Android lifecycle](https://developer.android.com/images/activity_lifecycle.png)

Pour diminuer la consommation électrique et l'utilisation de resources système, il faudrait désactiver la connexion Bluetooth (ou autre) lorsque l'application passe dans les méthodes `onPause()` ou `onStop()`. Puis réactiver la connexion quand l'application passe dans `onStart()` ou `onResume()`.

### 9. Facultatif – Question Bonus - S’il vous reste du temps, nous vous conseillons de le consacrer à mettre en place la résolution des permissions au runtime.

La demande de permissions au runtime a été implémentée (voir code).