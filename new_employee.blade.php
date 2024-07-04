<html>
<head>
    <title>TaskPlanner Account Information</title>
</head>
<body>
    <p>Hello {{ $prenom }} {{ $nom }},</p>
    <p>Your TaskPlanner account has been created. Here are your account details:</p>
    <p><strong>CIN:</strong> {{ $cin }}</p>
    <p><strong>Nom:</strong> {{ $nom }}</p>
    <p><strong>Prénom:</strong> {{ $prenom }}</p>
    <p><strong>Adresse:</strong> {{ $adresse }}</p>
    <p><strong>Email:</strong> {{ $email }}</p>
    <p><strong>Numéro de téléphone:</strong> {{ $tel }}</p>
    <p><strong>Mot de passe:</strong> {{ $mdp }}</p>
    <p>Please change your password after logging in for the first time.</p>
</body>
</html>
