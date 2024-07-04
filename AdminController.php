<?php

namespace App\Http\Controllers;

use Illuminate\Support\Facades\DB;
use Illuminate\Http\Request;
use App\Models\Admin;

class AdminController extends Controller
{
    public function signUpAdmin(Request $request){
        $cin = $request->input('cin');
        $nom = $request->input('nom');
        $prenom = $request->input('prenom');
        $adresse = $request->input('adresse');
        $email = $request->input('email');
        $mdp = $request->input('mdp');
        $numtel = $request->input('tel');
    
        if (!empty($cin) && !empty($nom) && !empty($prenom) && !empty($adresse) && !empty($email) && !empty($mdp) && !empty($numtel)) {
            try {
                DB::table('admins')->insert([
                    'Cin' => $cin,
                    'Nom' => $nom,
                    'Prenom' => $prenom,
                    'Adresse' => $adresse,
                    'Email' => $email,
                    'MotDePasse' => $mdp,
                    'NumTel' => $numtel
                ]);
                echo "<html>
                        <head>
                            <title>TaskPlanner Account Information</title>
                        </head>
                        <body>
                            <p>Hello $prenom  $nom ,</p>
                            <p>Your TaskPlanner account has been created. Here are your account details:</p>
                            <p><strong>CIN:</strong> $cin </p>
                            <p><strong>Nom:</strong> $nom </p>
                            <p><strong>Prénom:</strong> $prenom </p>
                            <p><strong>Adresse:</strong> $adresse </p>
                            <p><strong>Email:</strong> $email </p>
                            <p><strong>Numéro de téléphone:</strong> $numtel </p>
                            <p><strong>Mot de passe:</strong> $mdp </p>
                            <p>Please change your password after logging in for the first time.</p>
                        </body>
                    </html>";
            } catch (\Exception $e) {
                return $e->getMessage();
            }
        } else {
            return back()->withErrors('erreur','echec de l"inscription');
        }

    }
    public function loginAdmin(Request $request){
            $email = $request->input('email');
            $mdp = $request->input('mdp');
            $admin = Admin::where('Email', $email)->firstOrFail();
        
            if ($admin->motDePasse === $mdp && $admin->email === $email) {
                    return view('ProfileAdmin')->with('admin', $admin);
                }else{
                    return redirect()->route('ConnexionEmployee')->with('erreur', 'échec de connexion');
                }
    }
}
