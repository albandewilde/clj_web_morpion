(ns webmorpion.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defn ttt []
  "Tic - Tac - Toe")

(defn play []
  ;; Get the player from the query string

  ;; Get the board from the query string

  ;; Display the modified grid

 "The player play his turn")

(defn endgame [player]
  ;; Get the winner from the query string
  (def p (Integer/parseInt player))

  ;; Display a page to congrat him
  (if (== 0 p) "Draw"
    (if (== 1 p) "player 1 won" "player 2 is the winner")))


(defroutes app-routes
  (GET "/" [] (ttt))
  (GET "/play" [] (play))
  (GET "/endgame" {params :query-params} (endgame (get params "p")))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
