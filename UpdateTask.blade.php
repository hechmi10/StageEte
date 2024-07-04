<!DOCTYPE html>
<html lang="en">

<head>
    <title>TaskPlanner</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <style id="" media="all">
        /* latin-ext */
        @font-face {
            font-family: 'Poppins';
            font-style: normal;
            font-weight: 300;
            font-display: swap;
            src: url(/fonts.gstatic.com/s/poppins/v21/pxiByp8kv8JHgFVrLDz8Z1JlFc-K.woff2) format('woff2');
            unicode-range: U+0100-02AF, U+0304, U+0308, U+0329, U+1E00-1E9F, U+1EF2-1EFF, U+2020, U+20A0-20AB, U+20AD-20C0, U+2113, U+2C60-2C7F, U+A720-A7FF;
        }

        /* latin */
        @font-face {
            font-family: 'Poppins';
            font-style: normal;
            font-weight: 300;
            font-display: swap;
            src: url(/fonts.gstatic.com/s/poppins/v21/pxiByp8kv8JHgFVrLDz8Z1xlFQ.woff2) format('woff2');
            unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+0304, U+0308, U+0329, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;
        }

        /* latin-ext */
        @font-face {
            font-family: 'Poppins';
            font-style: normal;
            font-weight: 400;
            font-display: swap;
            src: url(/fonts.gstatic.com/s/poppins/v21/pxiEyp8kv8JHgFVrJJnecmNE.woff2) format('woff2');
            unicode-range: U+0100-02AF, U+0304, U+0308, U+0329, U+1E00-1E9F, U+1EF2-1EFF, U+2020, U+20A0-20AB, U+20AD-20C0, U+2113, U+2C60-2C7F, U+A720-A7FF;
        }

        /* latin */
        @font-face {
            font-family: 'Poppins';
            font-style: normal;
            font-weight: 400;
            font-display: swap;
            src: url(/fonts.gstatic.com/s/poppins/v21/pxiEyp8kv8JHgFVrJJfecg.woff2) format('woff2');
            unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+0304, U+0308, U+0329, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;
        }

        /* latin-ext */
        @font-face {
            font-family: 'Poppins';
            font-style: normal;
            font-weight: 500;
            font-display: swap;
            src: url(/fonts.gstatic.com/s/poppins/v21/pxiByp8kv8JHgFVrLGT9Z1JlFc-K.woff2) format('woff2');
            unicode-range: U+0100-02AF, U+0304, U+0308, U+0329, U+1E00-1E9F, U+1EF2-1EFF, U+2020, U+20A0-20AB, U+20AD-20C0, U+2113, U+2C60-2C7F, U+A720-A7FF;
        }

        /* latin */
        @font-face {
            font-family: 'Poppins';
            font-style: normal;
            font-weight: 500;
            font-display: swap;
            src: url(/fonts.gstatic.com/s/poppins/v21/pxiByp8kv8JHgFVrLGT9Z1xlFQ.woff2) format('woff2');
            unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+0304, U+0308, U+0329, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;
        }

        /* latin-ext */
        @font-face {
            font-family: 'Poppins';
            font-style: normal;
            font-weight: 600;
            font-display: swap;
            src: url(/fonts.gstatic.com/s/poppins/v21/pxiByp8kv8JHgFVrLEj6Z1JlFc-K.woff2) format('woff2');
            unicode-range: U+0100-02AF, U+0304, U+0308, U+0329, U+1E00-1E9F, U+1EF2-1EFF, U+2020, U+20A0-20AB, U+20AD-20C0, U+2113, U+2C60-2C7F, U+A720-A7FF;
        }

        /* latin */
        @font-face {
            font-family: 'Poppins';
            font-style: normal;
            font-weight: 600;
            font-display: swap;
            src: url(/fonts.gstatic.com/s/poppins/v21/pxiByp8kv8JHgFVrLEj6Z1xlFQ.woff2) format('woff2');
            unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+0304, U+0308, U+0329, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;
        }

        /* latin-ext */
        @font-face {
            font-family: 'Poppins';
            font-style: normal;
            font-weight: 700;
            font-display: swap;
            src: url(/fonts.gstatic.com/s/poppins/v21/pxiByp8kv8JHgFVrLCz7Z1JlFc-K.woff2) format('woff2');
            unicode-range: U+0100-02AF, U+0304, U+0308, U+0329, U+1E00-1E9F, U+1EF2-1EFF, U+2020, U+20A0-20AB, U+20AD-20C0, U+2113, U+2C60-2C7F, U+A720-A7FF;
        }

        /* latin */
        @font-face {
            font-family: 'Poppins';
            font-style: normal;
            font-weight: 700;
            font-display: swap;
            src: url(/fonts.gstatic.com/s/poppins/v21/pxiByp8kv8JHgFVrLCz7Z1xlFQ.woff2) format('woff2');
            unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+0304, U+0308, U+0329, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;
        }

        /* latin-ext */
        @font-face {
            font-family: 'Poppins';
            font-style: normal;
            font-weight: 800;
            font-display: swap;
            src: url(/fonts.gstatic.com/s/poppins/v21/pxiByp8kv8JHgFVrLDD4Z1JlFc-K.woff2) format('woff2');
            unicode-range: U+0100-02AF, U+0304, U+0308, U+0329, U+1E00-1E9F, U+1EF2-1EFF, U+2020, U+20A0-20AB, U+20AD-20C0, U+2113, U+2C60-2C7F, U+A720-A7FF;
        }

        /* latin */
        @font-face {
            font-family: 'Poppins';
            font-style: normal;
            font-weight: 800;
            font-display: swap;
            src: url(/fonts.gstatic.com/s/poppins/v21/pxiByp8kv8JHgFVrLDD4Z1xlFQ.woff2) format('woff2');
            unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+0304, U+0308, U+0329, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;
        }

        /* latin-ext */
        @font-face {
            font-family: 'Poppins';
            font-style: normal;
            font-weight: 900;
            font-display: swap;
            src: url(/fonts.gstatic.com/s/poppins/v21/pxiByp8kv8JHgFVrLBT5Z1JlFc-K.woff2) format('woff2');
            unicode-range: U+0100-02AF, U+0304, U+0308, U+0329, U+1E00-1E9F, U+1EF2-1EFF, U+2020, U+20A0-20AB, U+20AD-20C0, U+2113, U+2C60-2C7F, U+A720-A7FF;
        }

        /* latin */
        @font-face {
            font-family: 'Poppins';
            font-style: normal;
            font-weight: 900;
            font-display: swap;
            src: url(/fonts.gstatic.com/s/poppins/v21/pxiByp8kv8JHgFVrLBT5Z1xlFQ.woff2) format('woff2');
            unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+0304, U+0308, U+0329, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;
        }
    </style>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="{{asset('css/style.css')}}">
    <meta name="robots" content="noindex, follow">
</head>

<body>
    <div class="wrapper d-flex align-items-stretch">
        <nav id="sidebar">
            <div class="p-4 pt-5">
                <a href="{{route('home')}}" class="img logo rounded-circle mb-5"
                    style="background-image: url(images/tache.png);"></a>
                <ul class="list-unstyled components mb-5">
                    <li>
                        <a href="{{ route('GestionEmployee') }}">
                            Gestion Employé
                        </a>
                    </li>
                    <li>
                        <a href="{{ route('ConnexionEmployee') }}">
                            Connexion Employé
                        </a>
                    </li>
                    <li>
                        <a href="{{ route('GestionProjet') }}">
                            Gestion projets
                        </a>
                    </li>
                    <li>
                        <a href="{{ route('GestionTache') }}">
                            Gestion taches
                        </a>
                    </li>
                </ul>
                <div class="footer">
                    <p>
                        Copyright &copy;
                        <script>
                            document.write(new Date().getFullYear());
                        </script> All rights reserved
                    </p>
                </div>
            </div>
        </nav>
        <div id="content" class="p-4 p-md-5">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid">
                    <button type="button" id="sidebarCollapse" class="btn btn-primary">
                        <i class="fa fa-bars"></i>
                        <span class="sr-only">Toggle Menu</span>
                    </button>
                    <button class="btn btn-dark d-inline-block d-lg-none ml-auto" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                        <i class="fa fa-bars"></i>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="nav navbar-nav ml-auto">
                            <li class="nav-item">
                                <a class="nav-link" href="{{ route('home') }}">Home</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="{{ route('GestionEmployee') }}">Gestion Employé</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="{{ route('ConnexionEmployee') }}">Connexion Employé</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="{{ route('GestionProjet') }}">Gestion Projets</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="{{ route('GestionTache') }}">Gestion Taches</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <form action="{{ route('Tache2') }}" method="post">
                    @csrf
                    <fieldset class="border p-4">
                        <legend class="w-auto px-2">Mise à jour de la tache</legend>
                        <div class="form-group">
                        <label>Nom du tache</label><br>
                        <input type="text" class="form-control" name="nomtache" placeholder="Nom du tache" required><br>
                        </div>
                        <div class="form-group">
                        <label>Date de début</label><br>
                        <input type="date" class="form-control" name="datedebut" placeholder="Date de début" required><br>
                        </div>
                        <div class="form-group">
                        <label>Date de fin</label><br>
                        <input type="date" class="form-control" name="datefin" placeholder="Date de fin" required><br>
                        </div>
                        <div class="form-group">
                        <label>Période du tache</label><br>
                        <input type="text" class="form-control" name="periode" placeholder="Période de stage" required><br>
                        </div>
                        <div class="form-group">
                        <label>Estimation du période du tache</label><br>
                        <input type="text" class="form-control" name="estimation" placeholder="Estimation" required><br>
                        </div>
                        <button type="submit" class="btn btn-primary">Mettre à jour le tache<br>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
    <footer class="text-center mt-5">
        <p>Tous les droits sont réservés, TaskPlanner, réalisé par SW Consulting, 2024@</p>
    </footer>
</body>
<script src="{{asset('js/jquery.min.js')}}"></script>
    <script src="{{asset('js/popper.js')}}"></script>
    <script src="{{asset('js/bootstrap.min.js')}}"></script>
    <script src="{{asset('js/main.js')}}"></script>
    <script defer src="https://static.cloudflareinsights.com/beacon.min.js/vcd15cbe7772f49c399c6a5babf22c1241717689176015"
        integrity="sha512-ZpsOmlRQV6y907TI0dKBHq9Md29nnaEIPlkf84rnaERnq6zvWvPUqr2ft8M1aS28oN72PdrCzSjY4U6VaAw1EQ=="
        data-cf-beacon='{"rayId":"89a6520508825bcd","version":"2024.4.1","token":"cd0b4b3a733644fc843ef0b185f98241"}'
        crossorigin="anonymous"></script>

</html>
