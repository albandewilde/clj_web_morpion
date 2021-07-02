(ns webmorpion.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [selmer.parser :as parser]))

(defn ttt []
  (parser/render-file "./templates/home.html" {:list ( range 3 )}))

(defn play []
  ;; Get the player from the query string

  ;; Get the board from the query string

  ;; Display the modified grid

 "The player play his turn")

(defn endgame [player]
  ;; Get the winner from the query string
  (def p (Integer/parseInt player))

  ;; Display a page to congrat him
  (parser/render-file "./templates/win.html" {:p p}))


(defroutes app-routes
  (GET "/" [] (ttt))
  (GET "/play" [] (play))
  (GET "/endgame" {params :query-params} (endgame (get params "p")))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
