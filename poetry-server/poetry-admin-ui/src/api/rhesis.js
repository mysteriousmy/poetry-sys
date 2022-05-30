import service from "../utils/request";

export function FindAllrhesis() {
  return service({
    url: "/admins/rhesis",
    method: "get",
  });
}

export function Saverhesis(data) {
  return service({
    url: "/admins/rhesis",
    method: "post",
    data: data,
  });
}
export function Updaterhesis(data) {
  return service({
    url: "/admins/rhesis",
    method: "put",
    data: data,
  });
}
export function DeleterhesisById(id) {
  return service({
    url: "/admins/rhesis/" + id,
    method: "delete",
  });
}
