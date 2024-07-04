<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Projet extends Model
{
    protected $table='projets';
    protected $fillable=['projectId','nomProjet','dateDebut','dateFin'];
    use HasFactory;
}
