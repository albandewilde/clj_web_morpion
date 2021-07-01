(ns webmorpion.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (GET "/" [] "Tic - Tac - Toe")
  (GET "/play" [] "The player play his turn")
  (GET "/endgame" [] "The game is finish")
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
