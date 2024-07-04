<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use App\Models\Tache;
use App\Models\Employee;
use App\Models\Projet;
use App\Models\Admin;
class TacheController extends Controller
{
    public function addTask(Request $request){
        $nom=$request->input('nomtache');
        $debut=$request->input('datedebut');
        $fin=$request->input('datefin');
        $estimation=$request->input('estimation');
        $nomprojet=$request->input('projectname');
        $nomemploye=$request->input('nom');
        if(!empty($nom) && !empty($debut) && !empty($fin) && !empty($estimation) && !empty($nomprojet) && !empty($nomemploye)){
            try{
                DB::table('taches')->insert([
                    'nomTache' => $nom,
                    'dateDebut' => $debut,
                    'dateFin' => $fin,
                    'estimation' => $estimation,
                    'taskProjectName' => $nomprojet,
                    'nomEmployé' => $nomemploye
                ]);
                return view('PageacceuilTaskPlanner')->with('success','Tache ajouté');
            }catch(QueryException $e){
                $e.getMessage();
            }
        }else{
            return back()->withErrors('erreur','Tache non planifié');
        }
    }
    public function updateTask(Request $request){
        $nom=$request->input('nomtache');
        $debut=$request->input('datedebut');
        $fin=$request->input('datefin');
        $estimation=$request->input('estimation');
        $tache=Tache::where('nomTache',$nom)->firstOrFail();
        
        if($tache){
            DB::table('taches')->update(['dateDebut' => $debut,'dateFin'=>$fin,'estimation' => $estimation]);
            return view('PageacceuilTaskPlanner')->with('success','mise à jour complet');
        }else{
            return back()->withErrors('erreur','mise à jour échu');
        }
    }
    public function deleteTask(Request $request){
        $tache=Tache::first();
        DB::table('taches')->where('nomTache',$tache->nomTache)->delete();
        return view('PageacceuilTaskPlanner')->with('succés','Tache supprimée');
    }
    public function goToTache(Request $request){
        $projets=Projet::all();
        $employees=Employee::all();
        $taches=Tache::all();
        return view('GestionTache')->with('taches',$taches)->with('employees',$employees)->with('projets',$projets);
    }
    public function searchTask(Request $request){
        $query = $request->input('query');
        $projets=Projet::all();
        $employees=Employee::all();
        $taches = Tache::where('id', 'LIKE', "%$query%")
                    ->orWhere('nomTache', 'LIKE', "%$query%")
                    ->paginate(10);

    return view('GestionTache', compact('taches','projets','employees'));
    }
}
