<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use App\Models\Employee;
use App\Models\Projet;
use App\Models\Admin;
class ProjetController extends Controller
{
    public function addProject(Request $request){
        $id=$request->input('projetid');
        $nom=$request->input('nomprojet');
        $debut=$request->input('datedebut');
        $fin=$request->input('datefin');
        if(!empty($id) && !empty($nom) && !empty($debut) && !empty($fin)){
            try{
                DB::table('projets')->insert([
                    'projectId' => $id,
                    'nomProjet' => $nom,
                    'dateDebut' => $debut,
                    'dateFin' => $fin
                ]);
            }catch(SQLException $e){
                $e.getMessage();
            }
            return view('PageacceuilTaskPlanner')->with('success','Ajout succés');
        }else{
            return back()->withErrors('erreur','Erreur ajout du projet');
        }
    }
    public function updateProject(Request $request){
        $id=$request->input('id');
        $debut=$request->input('debut');
        $fin=$request->input('fin');
        $projet=Projet::where('projectId',$id)->firstOrFail();
        if($projet){
            DB::table('projets')->where('projectId',$id)->update(['dateDebut' => $debut]);
            DB::table('projets')->where('projectId',$id)->update(['dateFin' => $fin]);
            return view('PageacceuilTaskPlanner')->with('success','mise à jour succés');
        }else{
            return back()->withErrors('erreur','problème du mise à jour');
        }
    }
    public function deleteProject(Request $request){
        $projet=Projet::first();
        DB::table('projets')->where('projectId',$projet->projectId)->delete();
        return view('PageacceuilTaskPlanner')->with('success','Projet supprimé');
    }
    public function goToProjet(Request $request){
        $projets=Projet::all();
        return view('GestionProjet')->with('projets',$projets);
    }
    public function searchProject(Request $request){
        $query = $request->input('query');
        $projets = Projet::where('projectId', 'LIKE', "%$query%")
                    ->orWhere('nomProjet', 'LIKE', "%$query%")
                    ->paginate(10);

    return view('GestionProjet', compact('projets'));
    }
}
