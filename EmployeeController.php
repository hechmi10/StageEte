<?php

namespace App\Http\Controllers;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Mail;
use Illuminate\Http\Request;
use App\Models\Employee;
use App\Models\Projet;
use App\Models\Tache;
use App\Models\Admin;
class EmployeeController extends Controller
{
    public function addEmployee(Request $request) {
        $cin = $request->input('cin');
        $nom = $request->input('nom');
        $prenom = $request->input('prenom');
        $adresse = $request->input('adresse');
        $email = $request->input('email');
        $mdp = $request->input('mdp');
        $numtel = $request->input('tel');
    
        if (!empty($cin) && !empty($nom) && !empty($prenom) && !empty($adresse) && !empty($email) && !empty($mdp) && !empty($numtel)) {
            try {
                DB::table('employees')->insert([
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
    public function goToProfile(Request $request){
    $email = $request->input('email');
    $mdp = $request->input('mdp');
    $employee = Employee::where('Email', $email)->firstOrFail();

    if ($employee->motDePasse === $mdp && $employee->email === $email) {
            $projets = Projet::all();
            $taches= Tache::all();
            return view('ProfileEmployee')->with('employee', $employee)->with('projets', $projets)->with('taches',$taches);
        }else{
            return redirect()->route('ConnexionEmployee')->with('erreur', 'échec de connexion');
        }
    }

    public function updatePassword(Request $request){
        $email=$request->input('email');
        $old=$request->input('mdp');
        $new=$request->input('nouveaumdp');
        $employee=DB::table('employees')->where('Email',$email)->first();
        if($employee->motDePasse === $old){
            DB::table('employees')->where('Email',$email)->update(['MotDePasse' => $new]);
            return view('PageacceuilTaskPlanner')->with('status', 'mot de passe changé successivement');
        }else{
            return back()->withErrors(['old'=>"Veuillez réessayer s'il vous plait"]);
        }
    }
    public function deleteAccount(Request $request){
        $user = Employee::first();
        $admin=Admin::all();
        DB::table('employees')->where('Cin', $user->Cin)->delete();
        return view('PageacceuilTaskPlanner')->with('status', 'Suppression du compte réussi');
    }

    
    public function goToEmployee(Request $request){
        $employees=Employee::all();
        return view('GestionEmployee')->with('employees',$employees);
    }
    public function searchEmployee(Request $request){
        $query = $request->input('query');
        $employees = Employee::where('cin', 'LIKE', "%$query%")
                    ->orWhere('nom', 'LIKE', "%$query%")
                    ->orWhere('prenom', 'LIKE', "%$query%")
                    ->paginate(10);

    return view('GestionEmployee', compact('employees'));
    }
}
