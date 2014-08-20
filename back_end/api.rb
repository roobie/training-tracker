require 'sinatra'
require 'sinatra/cross_origin'
require 'sinatra/reloader' if development?
require 'json'

configure do
  enable :cross_origin
end

m_data = Hash.new

get '/' do
  return "HELLO"
end

get '/v1/movements' do
  content_type :json
  return m_data.to_json
end

options '/v1/movements' do
  cross_origin
end

post '/v1/movements' do
  request.body.rewind  # in case someone already read it
  data = JSON.parse request.body.read
  data.id = rand(10000)
  m_data[data.id] = data
  return data
end
