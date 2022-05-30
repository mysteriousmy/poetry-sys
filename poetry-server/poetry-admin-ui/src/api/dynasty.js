import service from "../utils/request";

export function FindAlldynasty() {
  return service({
    url: "/admins/dynasty",
    method: "get",
  });
}

export function Savedynasty(data) {
  return service({
    url: "/admins/dynasty",
    method: "post",
    data: data,
  });
}
export function Updatedynasty(data) {
  return service({
    url: "/admins/dynasty",
    method: "put",
    data: data,
  });
}
export function DeletedynastyById(id) {
  return service({
    url: "/admins/dynasty/" + id,
    method: "delete",
  });
}
