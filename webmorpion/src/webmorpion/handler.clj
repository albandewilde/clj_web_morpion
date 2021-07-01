(ns webmorpion.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [selmer.parser :as parser])

(defn ttt []
  parser/render-file "home.html")

(defn play []
  ;; Get the player from the query string

  ;; Get the board from the query string

  ;; Display the modified grid

 "The player play his turn")

(defn endgame []
  ;; Get the winner from the query string

  ;; Display a page to congrat him

 "The game is finish")

(defroutes app-routes
  (GET "/" [] (ttt))
  (GET "/play" [] (play))
  (GET "/endgame" [] (endgame))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
